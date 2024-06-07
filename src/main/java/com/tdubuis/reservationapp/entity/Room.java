package com.tdubuis.reservationapp.entity;

import com.tdubuis.reservationapp.dto.request.RoomRequest;
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
public class Room {
    @Id
    private String uid;

    @Column(length = 128)
    private String name;

    private Integer seats;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "cinemaId", nullable = false)
    private Cinema cinema;

    @OneToMany(mappedBy = "room")
    private List<Seance> seances;

    public void updateIfNotNull(RoomRequest roomRequest) {
        if (roomRequest.getUid() != null) {
            this.uid = roomRequest.getUid();
        }
        if (roomRequest.getName() != null && !roomRequest.getName().isBlank()) {
            this.name = roomRequest.getName();
        }
        if (roomRequest.getSeats() != null) {
            this.seats = roomRequest.getSeats();
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
