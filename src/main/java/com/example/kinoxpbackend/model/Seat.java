package com.example.kinoxpbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatID;
    private int seat;
    private int line; // Use proper escaping for reserved keyword
    private double price;
    private boolean isReserved; // Renamed this to match the column name in the table

    @ManyToOne
    @JoinColumn(name = "theaterID")
    private Theater theater;
}


