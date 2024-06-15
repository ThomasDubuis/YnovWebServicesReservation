package com.tdubuis.reservationapp.entity;

import com.tdubuis.reservationapp.dto.request.SeanceRequest;
import com.tdubuis.reservationapp.exception.UidAlreadyExistOrNotConformException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Seance {
    @Id
    private String uid;

    private String movie;

    private Date date;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = false)
    private Room room;

    @OneToMany(mappedBy = "seance")
    private List<Reservation> reservations;

    public void updateIfNotNull(SeanceRequest seanceRequest) {
        if (seanceRequest.getMovie() != null && !seanceRequest.getMovie().isBlank()) {
            this.movie = seanceRequest.getMovie();
        }
        if (seanceRequest.getDate() != null) {
            this.date = seanceRequest.getDate();
        }
    }

    @PrePersist
    protected void onCreate() {
        if (Objects.isNull(this.uid)) {
            this.uid = UUID.randomUUID().toString();
        } else {
            try {
                UUID uuid = UUID.fromString(this.uid);
                this.uid = uuid.toString();
            } catch (IllegalArgumentException ex) {
                throw new UidAlreadyExistOrNotConformException("Uid not conform to UUID V4");
            }
        }
    }
}
