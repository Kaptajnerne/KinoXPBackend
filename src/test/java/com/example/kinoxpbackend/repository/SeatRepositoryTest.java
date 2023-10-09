package com.example.kinoxpbackend.repository;


import com.example.kinoxpbackend.model.Seat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SeatRepositoryTest {

    @Autowired
    private SeatRepository seatRepository;

    @Test
    public void testSeatRepositorySave() {
        Seat seat = new Seat();
        seat.setSeat(42);
        seat.setLine(5);
        seat.setPrice(10.5);
        seat.setReserved(false);

        Seat savedSeat = seatRepository.save(seat);

        assertThat(savedSeat.getSeatID()).isNotNull();
        assertThat(savedSeat.getSeat()).isEqualTo(42);
        assertThat(savedSeat.getLine()).isEqualTo(5);
        assertThat(savedSeat.getPrice()).isEqualTo(10.5);
        assertThat(savedSeat.isReserved()).isFalse();
    }

    @Test
    public void testFindAllSeats() {
        Seat seat1 = new Seat();
        seat1.setSeat(1);
        seat1.setLine(1);
        seat1.setPrice(10.0);
        seat1.setReserved(false);
        seatRepository.save(seat1);

        Seat seat2 = new Seat();
        seat2.setSeat(2);
        seat2.setLine(2);
        seat2.setPrice(12.5);
        seat2.setReserved(true);
        seatRepository.save(seat2);

        List<Seat> seats = seatRepository.findAll();

        assertThat(seats).isNotEmpty();
        assertThat(seats).hasSize(2);

        assertThat(seats).contains(seat1, seat2);
    }
}


