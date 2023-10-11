package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.model.Movie;
import com.example.kinoxpbackend.model.Theater;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ShowtimeDTO {
    private int showtimeID;
    private LocalDate date;
    private LocalTime time;
    private Movie movie;
    private Theater theater;
}

