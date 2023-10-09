package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.dto.SeatShowtimeDTO;
import com.example.kinoxpbackend.model.Movie;
import com.example.kinoxpbackend.model.Showtime;
import com.example.kinoxpbackend.model.Theater;
import com.example.kinoxpbackend.repository.MovieRepository;
import com.example.kinoxpbackend.repository.ShowtimeRepository;
import com.example.kinoxpbackend.repository.TheaterRepository;
import com.example.kinoxpbackend.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/showtimes")
@CrossOrigin
public class ShowtimeController {


    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping()
    public ResponseEntity<List<Showtime>> findAll(@RequestParam(name = "movieID", required = false) Integer movieID) {
        List<Showtime> showTimes;

        //Check if movieID is provided
        if (movieID != null) {
            showTimes = showtimeRepository.findByMovie_MovieID(movieID);
        } else {
            showTimes = showtimeRepository.findAll();
        }

        return ResponseEntity.ok().body(showTimes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Showtime> findById(@PathVariable int id) {
        Optional<Showtime> showTime = showtimeRepository.findById(id);
        return showTime.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<SeatShowtimeDTO> createShowtime(@RequestBody Showtime showtime, @RequestParam int theaterId, @RequestParam int movieId) {
        Theater theater = theaterRepository.findTheaterByTheaterID(theaterId);
        Movie movie = movieRepository.findMovieByMovieID(movieId);

        if (theater == null || movie == null) {
            return ResponseEntity.badRequest().build();
        } else
        {
            SeatShowtimeDTO createdShowtime = showtimeService.createShowtime(showtime, theater, movie);
            return ResponseEntity.ok().body(createdShowtime);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<Showtime> showTime = showtimeRepository.findById(id);
        if (showTime.isPresent()) {
            showtimeRepository.deleteById(id);
            return ResponseEntity.ok("Showtime deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Showtime> update(@PathVariable int id, @RequestBody Showtime updatedShowtime) {
        Optional<Showtime> existingShowtime = showtimeRepository.findById(id);
        if (existingShowtime.isPresent()) {
            updatedShowtime.setShowtimeID(id);
            Showtime savedShowtime = showtimeRepository.save(updatedShowtime);
            return ResponseEntity.ok().body(savedShowtime);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


