package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.model.SeatShowtime;
import com.example.kinoxpbackend.repository.SeatShowtimeRepository;
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

@WebMvcTest(SeatShowtimeController.class)
class SeatShowtimeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatShowtimeRepository seatShowtimeRepository;

    @Test
    void testFindAll() throws Exception {
        SeatShowtime seatShowtime = new SeatShowtime();
        seatShowtime.setSeatShowTimeID(1);
        seatShowtime.setPrice(10.0);

        when(seatShowtimeRepository.findAll()).thenReturn(Arrays.asList(seatShowtime));

        mockMvc.perform(get("/seatshowtimes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].seatShowTimeID").value(1))
                .andExpect(jsonPath("$[0].price").value(10.0));

        verify(seatShowtimeRepository, times(1)).findAll();
        verifyNoMoreInteractions(seatShowtimeRepository);
    }
}

