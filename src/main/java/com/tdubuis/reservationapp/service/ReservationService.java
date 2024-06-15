package com.tdubuis.reservationapp.service;

import com.tdubuis.reservationapp.config.SqsProperties;
import com.tdubuis.reservationapp.dto.request.ReservationRequest;
import com.tdubuis.reservationapp.entity.Reservation;
import com.tdubuis.reservationapp.entity.Seance;
import com.tdubuis.reservationapp.entity.Status;
import com.tdubuis.reservationapp.exception.*;
import com.tdubuis.reservationapp.repository.ReservationRepository;
import com.tdubuis.reservationapp.repository.SeanceRepository;
import com.tdubuis.reservationapp.utils.factory.ReservationFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeanceRepository seanceRepository;
    private final SqsClient sqsClient;
    private final SqsProperties sqsProperties;

    private static final String UNABLE_TO_FIND_SEANCE = "Unable to find Seance [id = %s]";
    private static final String UNABLE_TO_FIND_RESERVATION = "Unable to find Reservation [id = %s]";
    private static final String MOVIE_NOT_ASSIGN_TO_THE_SEANCE = "Movie [id = %s] not assigned to the Seance [id = %s]";
    private static final String SEATS_RESERVED_FOR_SEAT_AVAILABLE = "Seats reserved : %s, but only %s seats are available";
    private static final String SEATS_RESERVED_BUT_NO_SEATS_LEFT = "Seats reserved : %s, but no seats left";



    public Reservation createReservation(String movieUid, ReservationRequest reservationRequest) {
        Seance seance = seanceRepository.findById(reservationRequest.getSeance()).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_SEANCE, reservationRequest.getSeance())));
        if (!seance.getMovie().equals(movieUid)) {
            throw new ElementNotFoundException(String.format(MOVIE_NOT_ASSIGN_TO_THE_SEANCE, movieUid, reservationRequest.getSeance()));
        }
        //Get open reservation
        Integer rank = Math.toIntExact(seance.getReservations().stream().filter(reservation -> reservation.getStatus().equals(Status.open)).count());
        //Get Number of reservedSeats
        int numberOfReservedSeat = seance.getReservations().stream().filter(reservation -> reservation.getStatus().equals(Status.confirmed) || reservation.getStatus().equals(Status.open)).mapToInt(Reservation::getSeats).sum();
        int maxSeat = seance.getRoom().getSeats();
        if (maxSeat < numberOfReservedSeat + reservationRequest.getNbSeats()) {
            if (maxSeat - numberOfReservedSeat <= 0) {
                throw new NoSeatLeftException(String.format(SEATS_RESERVED_BUT_NO_SEATS_LEFT, reservationRequest.getNbSeats()));
            }
            throw new NoSeatLeftException(String.format(SEATS_RESERVED_FOR_SEAT_AVAILABLE, reservationRequest.getNbSeats(), maxSeat - numberOfReservedSeat));
        }

        Reservation reservation = ReservationFactory.toReservation(reservationRequest, seance, rank, Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)));
        return reservationRepository.save(reservation);
    }

    public String confirmReservation(String uid) {
        Reservation reservation = reservationRepository.findById(uid).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_RESERVATION, uid)));

        if (reservation.getStatus().equals(Status.confirmed)) {
            throw new AlreadyConfirmedException("Reservation already confirmed");
        }
        Date date = Date.from(Instant.now());
        if (reservation.getStatus().equals(Status.expired) || reservation.getExpiresAt().after(date)) {
            throw new ExpiredReservationException("Reservation expired");
        }
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(sqsClient.listQueues().queueUrls().stream().filter(queue -> queue.contains(sqsProperties.getQueueName())).findFirst().orElseThrow(() -> new ElementNotFoundException("Queue not found")))
                .messageGroupId(uid)
                .messageDeduplicationId(uid)
                .messageBody(reservation.getUid())
                .build();
        sqsClient.sendMessage(sendMessageRequest);
        return null;
    }

    public List<Reservation> getReservationByMovieId(String movieUid) {
        List<Seance> seances = seanceRepository.findAllByMovie(movieUid);

        //Get all reservation with the seance have the movieUid
        List<Reservation> reservations = seances.stream().map(Seance::getReservations).flatMap(Collection::stream).toList();
        if (reservations.isEmpty()) {
            throw new NoResultException("No reservations found");
        }
        return reservations;
    }

    public Reservation getReservationById(String uid) {
        return reservationRepository.findById(uid).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_RESERVATION, uid)));
    }
}
