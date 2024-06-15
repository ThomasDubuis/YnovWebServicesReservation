package com.tdubuis.reservationapp.dto.response;

import com.tdubuis.reservationapp.entity.Status;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class ReservationResponse {

    private String uid;

    private Integer rank;

    private Status status;

    private Date expiresAt;

    private Date createdAt;

    private Date updatedAt;
}
