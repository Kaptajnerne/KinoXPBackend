package com.example.kinoxpbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private int ageLimit;
    private String genre;
    private int duration; //In minutes
    private String movieImageUrl;

    @ManyToOne
    @JoinColumn(name = "Theatres", referencedColumnName = "theatreID")
    Theatres theatres;

}