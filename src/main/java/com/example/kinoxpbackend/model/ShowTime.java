package com.example.kinoxpbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class ShowTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showTimeID;
    private LocalDate date;
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "movieID", referencedColumnName = "movieID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theaterID")
    private Theater theater;
}
