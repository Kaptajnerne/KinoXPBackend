package com.example.kinoxpbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationID;
    private String email;
    private String name;
    private int age;
    private double fullPrice;

    @ManyToMany
    @JoinTable(
            name = "reservation_seat_showtimes",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_showtime_id")
    )
    private Set<SeatShowtime> seatShowtimes = new HashSet<>();
}




