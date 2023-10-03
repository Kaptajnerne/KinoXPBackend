package com.example.kinoxpbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theaterID;
    private int NumberOfTheater;
    private int seatsPrRow;

    @ManyToOne
    @JoinColumn(name = "showTime_Theater", referencedColumnName = "showTimeID")
    private ShowTime showTime;

}
