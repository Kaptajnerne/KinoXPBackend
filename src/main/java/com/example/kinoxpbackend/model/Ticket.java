package com.example.kinoxpbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int ticketID;
    private double price;

    @ManyToOne
    @JoinColumn(name = "Movie", referencedColumnName = "MovieId")
    Movie movie;

    @OneToOne(mappedBy = "ticket")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "Show", referencedColumnName = "ShowId")
    Show show;

    @ManyToOne
    @JoinColumn(name = "Theater", referencedColumnName = "TheaterId")
    Theater theater;

}
