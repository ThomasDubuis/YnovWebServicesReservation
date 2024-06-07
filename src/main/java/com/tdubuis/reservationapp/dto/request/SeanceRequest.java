package com.tdubuis.reservationapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class SeanceRequest {

    @NotBlank(message = "Movie can't be empty")
    private String movie;

    private Date date;
}
