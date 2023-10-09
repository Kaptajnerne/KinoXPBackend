package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
    List<Showtime> findByMovie_MovieID(int movieID);
}
