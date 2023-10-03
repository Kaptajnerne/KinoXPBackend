package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.model.Reservation;
import com.example.kinoxpbackend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping()
    public ResponseEntity<List<Reservation>> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return ResponseEntity.ok().body(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable int id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation) {
        Reservation createdReservation = reservationRepository.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            reservationRepository.deleteById(id);
            return ResponseEntity.ok("Reservation deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> update(@PathVariable int id, @RequestBody Reservation updatedReservation) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if (existingReservation.isPresent()) {
            updatedReservation.setReservationID(id);
            Reservation savedReservation = reservationRepository.save(updatedReservation);
            return ResponseEntity.ok().body(savedReservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
