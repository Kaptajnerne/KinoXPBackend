package com.example.kinoxpbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showtimeID;
    private LocalDate date;
    private LocalTime time;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movieID", referencedColumnName = "movieID")
    private Movie movie;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theaterID")
    private Theater theater;

    @OneToMany(mappedBy = "showTime", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private Set<SeatShowtime> seatShowtimes = new HashSet<>();

}
