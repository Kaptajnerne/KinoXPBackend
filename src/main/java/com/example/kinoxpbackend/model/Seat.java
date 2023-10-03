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

    /*@ManyToOne
    @JoinColumn(name = "theater", referencedColumnName = "theaterID")
    private Theater theater; //

    @OneToOne(mappedBy = "seat", cascade = CascadeType.ALL)
    private Ticket ticket;*/

}