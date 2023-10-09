package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.model.SeatShowtime;
import com.example.kinoxpbackend.repository.SeatShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seatshowtimes")
@CrossOrigin
public class SeatShowtimeController {

    @Autowired
    SeatShowtimeRepository seatShowtimeRepository;

    @GetMapping()
    public ResponseEntity<List<SeatShowtime>> findAll() {
        List<SeatShowtime> seatShowtimes = seatShowtimeRepository.findAll();
        return ResponseEntity.ok().body(seatShowtimes);
    }

}
