package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.model.Seat;
import com.example.kinoxpbackend.model.SeatShowtime;
import com.example.kinoxpbackend.model.Showtime;
import com.example.kinoxpbackend.repository.SeatShowtimeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SeatShowtimeRepositoryTest {

    @Autowired
    private SeatShowtimeRepository seatShowtimeRepository;

    @Test
    public void testSaveSeatShowtime() {
        Seat seat = new Seat();

        Showtime showtime = new Showtime();

        SeatShowtime seatShowtime = new SeatShowtime();
        seatShowtime.setPrice(10.0);
        seatShowtime.setReserved(false);
        seatShowtime.setSeat(seat);
        seatShowtime.setShowTime(showtime);

        SeatShowtime savedSeatShowtime = seatShowtimeRepository.save(seatShowtime);

        assertNotNull(savedSeatShowtime);
        assertNotNull(savedSeatShowtime.getSeatShowTimeID());

        SeatShowtime retrievedSeatShowtime = seatShowtimeRepository.findById(savedSeatShowtime.getSeatShowTimeID()).orElse(null);

        assertNotNull(retrievedSeatShowtime);
        assertEquals(savedSeatShowtime.getSeatShowTimeID(), retrievedSeatShowtime.getSeatShowTimeID());
        assertEquals(savedSeatShowtime.getPrice(), retrievedSeatShowtime.getPrice());
        assertEquals(savedSeatShowtime.isReserved(), retrievedSeatShowtime.isReserved());
        assertEquals(savedSeatShowtime.getSeat(), retrievedSeatShowtime.getSeat());
        assertEquals(savedSeatShowtime.getShowTime(), retrievedSeatShowtime.getShowTime());
    }

    @Test
    public void testDeleteSeatShowtime() {
        SeatShowtime seatShowtime = new SeatShowtime();
        seatShowtimeRepository.save(seatShowtime);

        seatShowtimeRepository.deleteById(seatShowtime.getSeatShowTimeID());

        SeatShowtime deletedSeatShowtime = seatShowtimeRepository.findById(seatShowtime.getSeatShowTimeID()).orElse(null);

        assertNull(deletedSeatShowtime);
    }

}

