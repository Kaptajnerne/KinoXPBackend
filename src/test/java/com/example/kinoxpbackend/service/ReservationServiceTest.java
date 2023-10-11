package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.model.Movie;
import com.example.kinoxpbackend.model.SeatShowtime;
import com.example.kinoxpbackend.model.Showtime;
import com.example.kinoxpbackend.repository.SeatShowtimeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ReservationServiceTest {

    @Mock
    private SeatShowtimeRepository seatShowtimeRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void testAgeIsWithinLimitOverLimit() {
        SeatShowtime seatShowtime = new SeatShowtime();
        Showtime showTime = new Showtime();
        Movie movie = new Movie();
        movie.setAgeLimit(18);
        showTime.setMovie(movie);
        seatShowtime.setShowTime(showTime);

        List<SeatShowtime> selectedSeatShowtimes = new ArrayList<>();
        selectedSeatShowtimes.add(seatShowtime);

        boolean isAgeWithinLimit = reservationService.isAgeWithinLimit(25, selectedSeatShowtimes);
        assertTrue(isAgeWithinLimit);
    }


    @Test
    void testAgeIsUnderLimit() {
        SeatShowtime seatShowtime = new SeatShowtime();
        Showtime showTime = new Showtime();
        Movie movie = new Movie();
        movie.setAgeLimit(18);
        showTime.setMovie(movie);
        seatShowtime.setShowTime(showTime);

        List<SeatShowtime> selectedSeatShowtimes = new ArrayList<>();
        selectedSeatShowtimes.add(seatShowtime);

        boolean isAgeWithinLimit = reservationService.isAgeWithinLimit(16, selectedSeatShowtimes);
        assertTrue(isAgeWithinLimit);
        //assertFalse(isAgeWithinLimit); activate this if u want the test to pass
    }
}

