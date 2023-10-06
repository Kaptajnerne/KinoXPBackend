package com.example.kinoxpbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatID;
    private int seat;
    private int line;
    private double price;
    private boolean isReserved;

    @ManyToOne
    @JoinColumn(name = "theaterID")
    private Theater theater;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private Set<SeatShowtime> seatShowtimes = new HashSet<>();
}