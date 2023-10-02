package com.example.kinoxpbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int showID;
    private LocalDate showDate;
    private LocalTime showTime;

    @ManyToOne
    @JoinColumn(name = "Movie", referencedColumnName = "movieID")
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "Theater", referencedColumnName = "theaterID")
    Theater theater;

    @OneToMany(mappedBy = "Ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();

}
