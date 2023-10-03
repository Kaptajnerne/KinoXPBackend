package com.example.kinoxpbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ReservationID;
    private int ReservationNumber;

    @ManyToOne
    @JoinColumn(name = "Showtime_Reservation", referencedColumnName = "showTimeID") // Specify column name for show association
    private ShowTime showTime;


}
