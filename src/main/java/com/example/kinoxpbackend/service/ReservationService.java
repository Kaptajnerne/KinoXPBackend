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
        reservation.setName(reservation.getName());
        reservation.setAge(reservation.getAge());
        reservation.setEmail(reservation.getEmail());
        reservation.setSeatShowtimes(new HashSet<>(selectedSeatShowtimes));
        int totalPrice = calculatePrice(selectedSeatShowtimes);
        reservation.setFullPrice(totalPrice);

        if (!isAgeWithinLimit(reservation.getAge(), selectedSeatShowtimes)) {
            throw new IllegalArgumentException("Hey you little shit, your not old enough, so pack your shit and get going.");
        }
        reservation = reservationRepository.save(reservation);

        return reservation;
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