package com.example.kinoxpbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Theatres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private int TheatreNumber;
    private int row;
    private int seat;
}
