package com.tdubuis.reservationapp.entity;

import com.tdubuis.reservationapp.dto.request.CinemaRequest;
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
public class Cinema {
    @Id
    private String uid;

    @Column(length = 128)
    private String name;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @OneToMany(mappedBy = "cinema")
    private List<Room> rooms;

    public void updateIfNotNull(CinemaRequest cinemaRequest) {
        if (cinemaRequest.getUid() != null) {
            this.uid = cinemaRequest.getUid();
        }
        if (cinemaRequest.getName() != null && !cinemaRequest.getName().isBlank()) {
            this.name = cinemaRequest.getName();
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
