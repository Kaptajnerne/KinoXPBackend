package com.example.kinoxpbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SeatShowtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatShowTimeID;
    private double price;
    private boolean isReserved;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "showtime_id")
    private Showtime showTime;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
