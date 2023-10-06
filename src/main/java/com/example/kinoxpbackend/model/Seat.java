package com.example.kinoxpbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatID;
    private int seat;
    private int line;
    private double price;
    private boolean isReserved;

    @ManyToOne
    @JoinColumn(name = "theaterID")
    private Theater theater;

    @ManyToOne
    @JoinColumn(name = "showTimeID")
    private ShowTime showTime;
}


