package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findTheaterByTheaterID(int id);
}
