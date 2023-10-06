package com.example.kinoxpbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showtimeID;
    private LocalDate date;
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "movieID", referencedColumnName = "movieID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theaterID")
    private Theater theater;

    @OneToMany(mappedBy = "showTime")
    @JsonIgnore
    private Set<SeatShowtime> seatShowtimes = new HashSet<>();

}
