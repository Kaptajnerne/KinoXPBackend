package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.model.Reservation;
import com.example.kinoxpbackend.model.Theater;
import com.example.kinoxpbackend.repository.ReservationRepository;
import com.example.kinoxpbackend.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/theatres")
@CrossOrigin
public class TheaterController {


    @Autowired
    private TheaterRepository theaterRepository;

    @GetMapping()
    public ResponseEntity<List<Theater>> findAll() {
        List<Theater> theaters = theaterRepository.findAll();
        return ResponseEntity.ok().body(theaters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theater> findById(@PathVariable int id) {
        Optional<Theater> theater = theaterRepository.findById(id);
        return theater.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Theater> create(@RequestBody Theater theater) {
        Theater createdTheater = theaterRepository.save(theater);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTheater);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<Theater> theater = theaterRepository.findById(id);
        if (theater.isPresent()) {
            theaterRepository.deleteById(id);
            return ResponseEntity.ok("Theatre deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Theater> update(@PathVariable int id, @RequestBody Theater updatedTheater) {
        Optional<Theater> existingTheater = theaterRepository.findById(id);
        if (existingTheater.isPresent()) {
            updatedTheater.setTheaterID(id);
            Theater savedTheater = theaterRepository.save(updatedTheater);
            return ResponseEntity.ok().body(savedTheater);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


