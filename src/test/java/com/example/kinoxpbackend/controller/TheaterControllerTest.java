package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.model.Theater;
import com.example.kinoxpbackend.repository.TheaterRepository;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TheaterController.class)
class TheaterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TheaterRepository theaterRepository;

    @Test
    void testFindAll() throws Exception {
        Theater theater = new Theater();
        theater.setTheaterID(1);
        theater.setSeatsPrLine(10);
        theater.setNumberOfLines(5);

        List<Theater> theaters = Arrays.asList(theater);

        when(theaterRepository.findAll()).thenReturn(theaters);

        mockMvc.perform(get("/theatres"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].theaterID").value(1))
                .andExpect(jsonPath("$[0].seatsPrLine").value(10))
                .andExpect(jsonPath("$[0].numberOfLines").value(5));

        verify(theaterRepository, times(1)).findAll();
        verifyNoMoreInteractions(theaterRepository);
    }

    @Test
    void testFindById() throws Exception {
        Theater theater = new Theater();
        theater.setTheaterID(1);
        theater.setSeatsPrLine(10);
        theater.setNumberOfLines(5);

        when(theaterRepository.findById(1)).thenReturn(Optional.of(theater));

        mockMvc.perform(get("/theatres/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.theaterID").value(1))
                .andExpect(jsonPath("$.seatsPrLine").value(10))
                .andExpect(jsonPath("$.numberOfLines").value(5));

        verify(theaterRepository, times(1)).findById(1);
        verifyNoMoreInteractions(theaterRepository);
    }

    @Test
    void testCreate() throws Exception {
        Theater theater = new Theater();
        theater.setTheaterID(1);
        theater.setSeatsPrLine(10);
        theater.setNumberOfLines(5);

        when(theaterRepository.save(any(Theater.class))).thenReturn(theater);

        mockMvc.perform(post("/theatres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"seatsPrLine\":10,\"numberOfLines\":5}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.theaterID").value(1))
                .andExpect(jsonPath("$.seatsPrLine").value(10))
                .andExpect(jsonPath("$.numberOfLines").value(5));

        verify(theaterRepository, times(1)).save(any(Theater.class));
        verifyNoMoreInteractions(theaterRepository);
    }

    @Test
    void testUpdate() throws Exception {
        Theater theater = new Theater();
        theater.setTheaterID(1);
        theater.setSeatsPrLine(12);
        theater.setNumberOfLines(5);

        when(theaterRepository.findById(1)).thenReturn(Optional.of(theater));
        when(theaterRepository.save(any(Theater.class))).thenReturn(theater);

        mockMvc.perform(put("/theatres/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"seatsPrLine\":12,\"numberOfLines\":5}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.theaterID").value(1))
                .andExpect(jsonPath("$.seatsPrLine").value(12)) // Updated value
                .andExpect(jsonPath("$.numberOfLines").value(5));

        verify(theaterRepository, times(1)).findById(1);
        verify(theaterRepository, times(1)).save(any(Theater.class));
        verifyNoMoreInteractions(theaterRepository);
    }

    @Test
    void testDelete() throws Exception {
        Theater theater = new Theater();
        theater.setTheaterID(1);
        theater.setSeatsPrLine(10);
        theater.setNumberOfLines(5);

        when(theaterRepository.findById(1)).thenReturn(Optional.of(theater));

        mockMvc.perform(delete("/theatres/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Theatre deleted"));

        verify(theaterRepository, times(1)).findById(1);
        verify(theaterRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(theaterRepository);
    }
}

