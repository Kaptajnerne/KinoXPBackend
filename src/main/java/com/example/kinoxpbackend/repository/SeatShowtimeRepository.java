package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.model.SeatShowtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatShowtimeRepository extends JpaRepository<SeatShowtime, Integer>{
    List<SeatShowtime> findByShowTimeShowtimeID(int showtimeId);
}
