package com.tdubuis.reservationapp.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class RoomResponse {

    private String uid;

    private String name;

    private Integer seats;

    private Date createdAt;

    private Date updatedAt;
}
