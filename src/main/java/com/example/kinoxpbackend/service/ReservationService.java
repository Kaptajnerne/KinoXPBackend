package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.model.*;
import com.example.kinoxpbackend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation, List<SeatShowtime> selectedSeatShowtimes) {
        int totalPrice = calculatePrice(selectedSeatShowtimes);

        if (!isAgeWithinLimit(reservation.getAge(), selectedSeatShowtimes)) {
            throw new IllegalArgumentException("Hey you're not old enough, so pack your stuff and get going.");
        }

        reservation.setFullPrice(totalPrice);
        reservation.setSeatShowtimes(new HashSet<>(selectedSeatShowtimes));

        Reservation createdReservation = reservationRepository.save(reservation);

        return createdReservation;
    }

    public int calculatePrice(List<SeatShowtime> selectedSeatShowtimes) {
        int totalPrice = 0;
        for (SeatShowtime seatShowtime : selectedSeatShowtimes) {
            totalPrice += seatShowtime.getPrice();
        }
        return totalPrice;
    }

    public boolean isAgeWithinLimit(int age, List<SeatShowtime> selectedSeatShowtimes) {
        for (SeatShowtime seatShowtime : selectedSeatShowtimes) {
            int ageLimit = seatShowtime.getShowTime().getMovie().getAgeLimit();
            if (age < ageLimit) {
                return false;
            }
        }
        return true;
    }
}
