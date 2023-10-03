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
    @Column(name = "ticket_id") // Specify column name for ticketID
    private int ticketID;
    private double price;

    /*@ManyToOne
    @JoinColumn(name = "movie_id") // Specify column name for movie association
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "seat_id") // Specify column name for seat association
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "show", referencedColumnName = "showID") // Specify column name for show association
    private Show show;

    @ManyToOne
    @JoinColumn(name = "theater_id") // Specify column name for theater association
    private Theater theater;*/

}
