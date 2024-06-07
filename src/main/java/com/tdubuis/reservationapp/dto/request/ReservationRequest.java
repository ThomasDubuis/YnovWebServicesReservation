package com.tdubuis.reservationapp.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationRequest {

    @NotBlank(message = "Seance can't be empty")
    private String seance;

    @NotBlank(message = "Room can't be empty")
    private String room;

    @NotNull(message = "nbSeats can't be null")
    @Min(value = 1, message = "nbSeats should be > 1 ")
    private Integer nbSeats;

}
