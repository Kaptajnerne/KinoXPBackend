package com.example.kinoxpbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int theaterID;
    private int theatreNumber;

    //@ManyToMany
    //@JoinTable(joinColumns = @JoinColumn(name = "theater_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    //private Set<Movie> movies = new HashSet<>();

    /*@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Seat> seats = new HashSet<>();

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Show> shows = new HashSet<>();*/
}
