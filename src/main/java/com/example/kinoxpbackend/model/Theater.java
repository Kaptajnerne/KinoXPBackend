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
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theaterID;
    private int theatreNumber; // changed from TheatreNumber to theatreNumber

    @ManyToMany
    @JoinTable(
            name = "Movie",
            joinColumns = @JoinColumn(name = "TheaterID"),
            inverseJoinColumns = @JoinColumn(name = "MovieID")
    )
    private Set<Movie> movies;

    /*@ManyToMany(mappedBy = "movies")
    private Set<Theater> theaters;*/

    @OneToMany(mappedBy = "Seat", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Seat> seats = new HashSet<>();

    @OneToMany(mappedBy = "Show", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Show> shows = new HashSet<>();


}
