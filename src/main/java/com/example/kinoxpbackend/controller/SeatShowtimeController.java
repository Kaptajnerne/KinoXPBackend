package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.model.SeatShowtime;
import com.example.kinoxpbackend.repository.SeatShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getByShowtimeId")
    public ResponseEntity<List<SeatShowtime>> getSeatShowtimesByShowtimeId(@RequestParam int showtimeId) {
        List<SeatShowtime> seatShowtimes = seatShowtimeRepository.findByShowTimeShowtimeID(showtimeId);
        if (seatShowtimes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(seatShowtimes);
        }
    }


}
