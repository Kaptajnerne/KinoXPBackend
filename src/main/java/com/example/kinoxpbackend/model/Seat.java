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
    private int row;
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "Theater", referencedColumnName = "theaterID")
    Theater theater;

    @OneToOne(mappedBy = "Seat")
    private Ticket ticket;

}
