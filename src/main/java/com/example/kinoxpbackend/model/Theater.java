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
    private int seatsPrLine;
    private int numberOfLines;
}
