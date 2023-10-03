package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.model.Seat;
import com.example.kinoxpbackend.model.Theater;
import com.example.kinoxpbackend.repository.SeatRepository;
import com.example.kinoxpbackend.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/seats")
@CrossOrigin
public class SeatController {

    @Autowired
    private SeatRepository seatRepository;

    @GetMapping()
    public ResponseEntity<List<Seat>> findAll() {
        List<Seat> seats = seatRepository.findAll();
        return ResponseEntity.ok().body(seats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> findById(@PathVariable int id) {
        Optional<Seat> seat = seatRepository.findById(id);
        return seat.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Seat> create(@RequestBody Seat seat) {
        Seat createdSeat = seatRepository.save(seat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSeat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<Seat> seat = seatRepository.findById(id);
        if (seat.isPresent()) {
            seatRepository.deleteById(id);
            return ResponseEntity.ok("Seat deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seat> update(@PathVariable int id, @RequestBody Seat updatedSeat) {
        Optional<Seat> existingSeat = seatRepository.findById(id);
        if (existingSeat.isPresent()) {
            updatedSeat.setSeatID(id);
            Seat savedSeat = seatRepository.save(updatedSeat);
            return ResponseEntity.ok().body(savedSeat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
