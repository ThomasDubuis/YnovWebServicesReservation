package com.tdubuis.reservationapp.service;

import com.tdubuis.reservationapp.config.SqsProperties;
import com.tdubuis.reservationapp.entity.Reservation;
import com.tdubuis.reservationapp.entity.Status;
import com.tdubuis.reservationapp.exception.ElementNotFoundException;
import com.tdubuis.reservationapp.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CronService {

    private final SqsClient sqsClient;
    private final SqsProperties sqsProperties;
    private final ReservationRepository reservationRepository;

    @Scheduled(fixedRate = 30000) //30 sec delay
    public void checkIfReservationExpired() {
        log.debug("Begin : Cron for set expiredReservation");
        List<Reservation> reservationExpired = reservationRepository.findAllByExpiresAtIsBeforeAndStatus(Date.from(Instant.now()), Status.open);
        reservationExpired.forEach(reservation -> reservation.setStatus(Status.expired));
        reservationRepository.saveAll(reservationExpired);
        log.debug("End : Cron for set expiredReservation");
    }

    @Scheduled(fixedRate = 5000) // 5 sec delay
    public void checkMessageInQueue() {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .maxNumberOfMessages(1)
                .queueUrl(sqsClient.listQueues().queueUrls().stream().filter(queue -> queue.contains(sqsProperties.getQueueName())).findFirst().orElseThrow(() -> new ElementNotFoundException("Queue not found")))
                .build();
        ReceiveMessageResponse messageResponse = sqsClient.receiveMessage(receiveMessageRequest);

        if(messageResponse.hasMessages()) {
            String reservationUid = messageResponse.messages().get(0).body();
            Reservation reservation = reservationRepository.findById(reservationUid).orElseThrow(() -> new ElementNotFoundException("Reservation not found"));
            reservation.setStatus(Status.confirmed);
            reservationRepository.save(reservation);

            SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                    .queueUrl(sqsClient.listQueues().queueUrls().stream().filter(queue -> queue.contains(sqsProperties.getFakeMail())).findFirst().orElseThrow(() -> new ElementNotFoundException("Queue (FakeMail) not found")))
                    .messageGroupId(reservationUid)
                    .messageDeduplicationId(reservationUid)
                    .messageBody("Votre reservation est ok : " + reservation.getUid())
                    .build();
            sqsClient.sendMessage(sendMessageRequest);
        }else {
            log.debug("No message in queue");
        }
    }
}
