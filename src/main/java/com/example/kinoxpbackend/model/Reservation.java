package com.example.kinoxpbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationID;
    private String email;
    private String name;
    private int age;
    private double fullPrice;

    @OneToMany(mappedBy = "reservation")
    private Set<SeatShowtime> seatShowtimes = new HashSet<>();
}



