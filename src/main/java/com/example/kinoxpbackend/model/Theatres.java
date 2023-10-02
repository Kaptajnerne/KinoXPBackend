package com.example.kinoxpbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Theatres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theatreID;
    private int theatreNumber; // changed from TheatreNumber to theatreNumber
    private int rowNum; // changed from row to rowNum
    private int seatNum; // changed from seat to seatNum


    @OneToMany(mappedBy = "Movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Movie> movies = new HashSet<>();


}
