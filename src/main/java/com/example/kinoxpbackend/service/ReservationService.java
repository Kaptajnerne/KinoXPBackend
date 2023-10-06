package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.model.*;
import com.example.kinoxpbackend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    //If the user making a reservation is not within the age limit they can't make a reservation.
    //maybe make age limit its own method and combine into createReservation method
    public Reservation createReservation(Reservation reservation, SeatShowtime seatShowtime) {
        return reservation;
    }

    //Calculate the cost of all the chosen seats
    public int calculatePrice () {
        int fullPrice = 0;
        return fullPrice;
    }

}
