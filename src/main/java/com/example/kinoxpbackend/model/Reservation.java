package com.example.kinoxpbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "showtimeID")
    private ShowTime showTime;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "seatID")
    private Seat seat;
}


