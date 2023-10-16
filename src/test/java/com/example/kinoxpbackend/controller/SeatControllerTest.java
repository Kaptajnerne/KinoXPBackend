package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.model.Seat;
import com.example.kinoxpbackend.repository.SeatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SeatController.class)
class SeatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatRepository seatRepository;

    @Test
    void testFindAll() throws Exception {
        Seat seat = new Seat();
        seat.setSeatID(1);
        seat.setSeat(1);

        when(seatRepository.findAll()).thenReturn(Arrays.asList(seat));

        mockMvc.perform(get("/seats"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].seatID").value(1))
                .andExpect(jsonPath("$[0].seat").value(1));

        verify(seatRepository, times(1)).findAll();
        verifyNoMoreInteractions(seatRepository);
    }

    @Test
    void testFindById() throws Exception {
        Seat seat = new Seat();
        seat.setSeatID(1);
        seat.setSeat(1);

        when(seatRepository.findById(1)).thenReturn(Optional.of(seat));

        mockMvc.perform(get("/seats/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.seatID").value(1))
                .andExpect(jsonPath("$.seat").value(1));

        verify(seatRepository, times(1)).findById(1);
        verifyNoMoreInteractions(seatRepository);
    }

    @Test
    void testCreate() throws Exception {
        Seat seat = new Seat();
        seat.setSeatID(1);
        seat.setSeat(1);

        when(seatRepository.save(any(Seat.class))).thenReturn(seat);

        mockMvc.perform(post("/seats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"seat\": 1}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.seatID").value(1))
                .andExpect(jsonPath("$.seat").value(1));

        verify(seatRepository, times(1)).save(any(Seat.class));
        verifyNoMoreInteractions(seatRepository);
    }

    @Test
    void testUpdate() throws Exception {
        Seat seat = new Seat();
        seat.setSeatID(1);
        seat.setSeat(1);

        when(seatRepository.findById(1)).thenReturn(Optional.of(seat));
        when(seatRepository.save(any(Seat.class))).thenReturn(seat);

        mockMvc.perform(put("/seats/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"seat\": 2}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.seatID").value(1))
                .andExpect(jsonPath("$.seat").value(1));

        verify(seatRepository, times(1)).findById(1);
        verify(seatRepository, times(1)).save(any(Seat.class));
        verifyNoMoreInteractions(seatRepository);
    }

    @Test
    void testDelete() throws Exception {
        Seat seat = new Seat();
        seat.setSeatID(1);
        seat.setSeat(1);

        when(seatRepository.findById(1)).thenReturn(Optional.of(seat));

        mockMvc.perform(delete("/seats/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Seat deleted"));

        verify(seatRepository, times(1)).findById(1);
        verify(seatRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(seatRepository);
    }
}

