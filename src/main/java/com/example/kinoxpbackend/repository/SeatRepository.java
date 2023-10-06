package com.example.kinoxpbackend.repository;


import com.example.kinoxpbackend.model.Seat;
import com.example.kinoxpbackend.model.SeatShowtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
