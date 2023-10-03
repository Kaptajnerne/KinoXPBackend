
package com.example.kinoxpbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showTimeID;
    private LocalDate DateOfShow;
    private LocalTime TimeOfShow;


@ManyToOne
    @JoinColumn(name = "movieID", referencedColumnName = "movieID")
    private Movie movie;

    @ManyToMany
    @JoinTable(name = "showtime_seat",
            joinColumns = @JoinColumn(name = "showtimeID"),
            inverseJoinColumns = @JoinColumn(name = "seatID"))
    private Set<Seat> seats = new HashSet<>();

    @OneToMany(mappedBy = "showTime", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Reservation> reservations = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "theaterID")
    private Theater theater;

}
