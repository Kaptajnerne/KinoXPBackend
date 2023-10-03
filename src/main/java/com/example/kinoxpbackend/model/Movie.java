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
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieID;
    private String title;
    private String description;
    private int ageLimit;
    private String genre;
    private int duration; //In minutes
    private String movieImageUrl;

    //@ManyToMany(mappedBy = "movies")
    //private Set<Theater> theaters = new HashSet<>();
/*
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Show> shows = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();*/


}
