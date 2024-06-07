package com.tdubuis.reservationapp.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoomRequest {

    private String uid;
    @NotBlank(message = "Name can't be empty")
    @Size(max = 128, message = "Name should be between 1 and 128 characters")
    private String name;

    @NotNull(message = "seats can't be null")
    @Min(value = 1, message = "nbSeats should be > 1 ")
    private Integer seats;
}
