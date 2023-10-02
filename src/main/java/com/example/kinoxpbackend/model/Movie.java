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

    @ManyToMany
    @JoinTable(
            name = "Theater",
            joinColumns = @JoinColumn(name = "movieID"),
            inverseJoinColumns = @JoinColumn(name = "theaterID")
    )
    private Set<Theater> theaters;

    @OneToMany(mappedBy = "Show", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Show> shows = new HashSet<>();

    @OneToMany(mappedBy = "Ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();

}