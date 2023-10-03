package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.model.Movie;
import com.example.kinoxpbackend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
