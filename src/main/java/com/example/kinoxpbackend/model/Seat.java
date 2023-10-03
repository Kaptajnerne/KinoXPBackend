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
    private int NumberOfRow;
    private int NumberOfSeat;
    private double price;

    @ManyToMany(mappedBy = "seats")
    private Set<ShowTime> showTimes = new HashSet<>();




}
