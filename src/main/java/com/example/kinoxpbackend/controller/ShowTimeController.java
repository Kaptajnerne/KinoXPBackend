package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.model.Reservation;
import com.example.kinoxpbackend.model.ShowTime;
import com.example.kinoxpbackend.repository.ReservationRepository;
import com.example.kinoxpbackend.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/showtimes")
@CrossOrigin
public class ShowTimeController {


    @Autowired
    private ShowTimeRepository showTimeRepository;

    @GetMapping()
    public ResponseEntity<List<ShowTime>> findAll() {
        List<ShowTime> showTimes = showTimeRepository.findAll();
        return ResponseEntity.ok().body(showTimes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowTime> findById(@PathVariable int id) {
        Optional<ShowTime> showTime = showTimeRepository.findById(id);
        return showTime.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<ShowTime> create(@RequestBody ShowTime showTime) {
        ShowTime createdShowTime = showTimeRepository.save(showTime);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShowTime);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<ShowTime> showTime = showTimeRepository.findById(id);
        if (showTime.isPresent()) {
            showTimeRepository.deleteById(id);
            return ResponseEntity.ok("Showtime deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowTime> update(@PathVariable int id, @RequestBody ShowTime updatedShowtime) {
        Optional<ShowTime> existingShowtime = showTimeRepository.findById(id);
        if (existingShowtime.isPresent()) {
            updatedShowtime.setShowTimeID(id);
            ShowTime savedShowtime = showTimeRepository.save(updatedShowtime);
            return ResponseEntity.ok().body(savedShowtime);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


