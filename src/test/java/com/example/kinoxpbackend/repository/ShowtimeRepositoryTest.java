package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.model.Movie;
import com.example.kinoxpbackend.model.Showtime;
import com.example.kinoxpbackend.model.Theater;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ShowtimeRepositoryTest {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Test
    public void testCreateShowtime() {

        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movieRepository.save(movie);

        Theater theater = new Theater();
        theater.setSeatsPrLine(10);
        theater.setNumberOfLines(5);
        theaterRepository.save(theater);

        Showtime showtime = new Showtime();
        showtime.setDate(LocalDate.now());
        showtime.setTime(LocalTime.of(14, 30));
        showtime.setMovie(movie);
        showtime.setTheater(theater);

        showtimeRepository.save(showtime);

        Showtime savedShowtime = showtimeRepository.findById(showtime.getShowtimeID()).orElse(null);

        assertNotNull(savedShowtime);

        assertEquals(LocalDate.now(), savedShowtime.getDate());
        assertEquals(LocalTime.of(14, 30), savedShowtime.getTime());
        assertEquals(movie, savedShowtime.getMovie());
        assertEquals(theater, savedShowtime.getTheater());
    }

    @Test
    public void testDeleteShowtime() {

        Showtime showtime = new Showtime();
        showtime.setDate(LocalDate.now());
        showtime.setTime(LocalTime.of(14, 30));

        showtimeRepository.save(showtime);

        showtimeRepository.deleteById(showtime.getShowtimeID());

        Showtime deletedShowtime = showtimeRepository.findById(showtime.getShowtimeID()).orElse(null);

        assertNull(deletedShowtime);
    }

    @Test
    public void testUpdateShowtime() {

        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movieRepository.save(movie);

        Theater theater = new Theater();
        theater.setSeatsPrLine(10);
        theater.setNumberOfLines(5);
        theaterRepository.save(theater);

        Showtime showtime = new Showtime();
        showtime.setDate(LocalDate.now());
        showtime.setTime(LocalTime.of(14, 30));
        showtime.setMovie(movie);
        showtime.setTheater(theater);
        showtimeRepository.save(showtime);

        showtime.setDate(LocalDate.now().plusDays(1));
        showtime.setTime(LocalTime.of(16, 0));

        showtimeRepository.save(showtime);

        Showtime updatedShowtime = showtimeRepository.findById(showtime.getShowtimeID()).orElse(null);

        assertNotNull(updatedShowtime);

        assertEquals(LocalDate.now().plusDays(1), updatedShowtime.getDate());
        assertEquals(LocalTime.of(16, 0), updatedShowtime.getTime());
        assertEquals(movie, updatedShowtime.getMovie());
        assertEquals(theater, updatedShowtime.getTheater());
    }

    @Test
    public void testGetAllShowtimes() {

        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movieRepository.save(movie);

        Theater theater = new Theater();
        theater.setSeatsPrLine(10);
        theater.setNumberOfLines(5);
        theaterRepository.save(theater);

        Showtime showtime1 = new Showtime();
        showtime1.setDate(LocalDate.now());
        showtime1.setTime(LocalTime.of(14, 30));
        showtime1.setMovie(movie);
        showtime1.setTheater(theater);
        showtimeRepository.save(showtime1);

        Showtime showtime2 = new Showtime();
        showtime2.setDate(LocalDate.now().plusDays(1));
        showtime2.setTime(LocalTime.of(16, 0));
        showtime2.setMovie(movie);
        showtime2.setTheater(theater);
        showtimeRepository.save(showtime2);

        List<Showtime> showtimes = showtimeRepository.findAll();

        assertNotNull(showtimes);
        assertEquals(2, showtimes.size());
    }

    @Test
    public void testFindByMovie_MovieID() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movieRepository.save(movie);

        Showtime showtime1 = new Showtime();
        showtime1.setDate(LocalDate.now());
        showtime1.setTime(LocalTime.of(14, 30));
        showtime1.setMovie(movie);
        showtimeRepository.save(showtime1);

        Showtime showtime2 = new Showtime();
        showtime2.setDate(LocalDate.now().plusDays(1));
        showtime2.setTime(LocalTime.of(16, 0));
        showtime2.setMovie(movie);
        showtimeRepository.save(showtime2);

        List<Showtime> showtimes = showtimeRepository.findByMovie_MovieID(movie.getMovieID());

        assertNotNull(showtimes);
        assertEquals(2, showtimes.size());
    }

}
