package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.model.Seat;
import com.example.kinoxpbackend.model.Showtime;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SeatShowtimeDTO {

    private Showtime showtime;
    private List<Seat> seats;
}
