package com.example.kinoxpbackend.repository;


import com.example.kinoxpbackend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}

