package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.model.Reservation;
import com.example.kinoxpbackend.repository.ReservationRepository;
import com.example.kinoxpbackend.repository.SeatShowtimeRepository;
import com.example.kinoxpbackend.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private SeatShowtimeRepository seatShowtimeRepository;

    @Test
    void testFindById() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setReservationID(1);
        reservation.setEmail("test@example.com");

        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));

        mockMvc.perform(get("/reservations/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.reservationID").value(1))
                .andExpect(jsonPath("$.email").value("test@example.com"));

        verify(reservationRepository, times(1)).findById(1);
        verifyNoMoreInteractions(reservationRepository);
    }

    @Test
    void testCreateReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setReservationID(1);
        reservation.setEmail("test@example.com");

        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        when(reservationService.isAgeWithinLimit(anyInt(), any())).thenReturn(true);

        mockMvc.perform(post("/reservations/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"age\":25,\"fullPrice\":50.0,\"seatShowtimeIds\":[1,2]}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.reservationID").value(1))
                .andExpect(jsonPath("$.email").value("test@example.com"));

        verify(reservationRepository, times(1)).save(any(Reservation.class));
        verifyNoMoreInteractions(reservationRepository);
    }

    @Test
    void testUpdate() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setReservationID(1);
        reservation.setEmail("updated@example.com");

        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        mockMvc.perform(put("/reservations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"updated@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.reservationID").value(1))
                .andExpect(jsonPath("$.email").value("updated@example.com"));

        verify(reservationRepository, times(1)).findById(1);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
        verifyNoMoreInteractions(reservationRepository);
    }

    @Test
    void testDelete() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setReservationID(1);
        reservation.setEmail("test@example.com");

        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));

        mockMvc.perform(delete("/reservations/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Reservation deleted"));

        verify(reservationRepository, times(1)).findById(1);
        verify(reservationRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(reservationRepository);
    }
}

