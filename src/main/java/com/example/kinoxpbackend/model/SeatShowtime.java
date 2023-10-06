package com.example.kinoxpbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SeatShowtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatShowTimeID;
    private double price;
    private boolean isReserved;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showTime;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
