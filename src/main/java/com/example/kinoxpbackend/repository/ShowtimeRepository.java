package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
}
