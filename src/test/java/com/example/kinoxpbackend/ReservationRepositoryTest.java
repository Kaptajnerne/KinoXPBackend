package com.example.kinoxpbackend;

import com.example.kinoxpbackend.model.Reservation;
import com.example.kinoxpbackend.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void testCreateReservation() {
        Reservation reservation = new Reservation();
        reservation.setEmail("test@example.com");
        reservation.setName("Test User");
        reservation.setAge(30);
        reservation.setFullPrice(50.0);

        reservationRepository.save(reservation);

        Reservation savedReservation = reservationRepository.findById(reservation.getReservationID()).orElse(null);

        assertNotNull(savedReservation);

        assertEquals("test@example.com", savedReservation.getEmail());
        assertEquals("Test User", savedReservation.getName());
        assertEquals(30, savedReservation.getAge());
        assertEquals(50.0, savedReservation.getFullPrice());
    }
}
