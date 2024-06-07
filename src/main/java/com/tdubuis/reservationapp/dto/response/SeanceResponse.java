package com.tdubuis.reservationapp.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class SeanceResponse {

    private String uid;

    private String movie;

    private Date date;

    private Date createdAt;

    private Date updatedAt;
}
