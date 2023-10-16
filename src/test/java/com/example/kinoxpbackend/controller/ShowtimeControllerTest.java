package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.dto.SeatShowtimeDTO;
import com.example.kinoxpbackend.model.Movie;
import com.example.kinoxpbackend.model.Showtime;
import com.example.kinoxpbackend.model.Theater;
import com.example.kinoxpbackend.repository.MovieRepository;
import com.example.kinoxpbackend.repository.ShowtimeRepository;
import com.example.kinoxpbackend.repository.TheaterRepository;
import com.example.kinoxpbackend.service.ShowtimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.lang.reflect.Field;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShowtimeController.class)
class ShowtimeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShowtimeRepository showtimeRepository;

    @MockBean
    private ShowtimeService showtimeService;

    @MockBean
    private TheaterRepository theaterRepository;

    @MockBean
    private MovieRepository movieRepository;

    //virker ikke
    @Test
    void testCreateShowtime() throws Exception {
        SeatShowtimeDTO seatShowtimeDTO = new SeatShowtimeDTO();
        setPrivateField(seatShowtimeDTO, "showtimeID", 1);

        when(showtimeService.createShowtime(any(Showtime.class), any(Theater.class), any(Movie.class)))
                .thenReturn(seatShowtimeDTO);

        mockMvc.perform(post("/showtimes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"showtimeID\":1,\"theater\":{\"theaterID\":1},\"movie\":{\"movieID\":1}}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.showtimeID").value(1));

        verify(showtimeService, times(1)).createShowtime(any(Showtime.class), any(Theater.class), any(Movie.class));
        verifyNoMoreInteractions(showtimeService);
    }

    private void setPrivateField(Object object, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
        field.setAccessible(false);
    }


    @Test
    void testUpdateShowtime() throws Exception {
        Showtime updatedShowtime = new Showtime();
        updatedShowtime.setShowtimeID(1);

        when(showtimeRepository.findById(1)).thenReturn(Optional.of(new Showtime()));
        when(showtimeRepository.save(any(Showtime.class))).thenReturn(updatedShowtime);

        mockMvc.perform(put("/showtimes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"showtimeID\":1,\"theater\":{\"theaterID\":1},\"movie\":{\"movieID\":1}}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.showtimeID").value(1));

        verify(showtimeRepository, times(1)).findById(1);
        verify(showtimeRepository, times(1)).save(any(Showtime.class));
        verifyNoMoreInteractions(showtimeRepository);
    }

    @Test
    void testDeleteShowtime() throws Exception {
        Showtime showtime = new Showtime();
        showtime.setShowtimeID(1);

        when(showtimeRepository.findById(1)).thenReturn(Optional.of(showtime));

        mockMvc.perform(delete("/showtimes/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Showtime deleted"));

        verify(showtimeRepository, times(1)).findById(1);
        verify(showtimeRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(showtimeRepository);
    }

    @Test
    void testFindAll() throws Exception {
        List<Showtime> showtimes = new ArrayList<>();
        when(showtimeRepository.findAll()).thenReturn(showtimes);

        mockMvc.perform(get("/showtimes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(showtimeRepository, times(1)).findAll();
        verifyNoMoreInteractions(showtimeRepository);
    }

    @Test
    void testFindById() throws Exception {
        Showtime showtime = new Showtime();
        showtime.setShowtimeID(1);

        when(showtimeRepository.findById(1)).thenReturn(Optional.of(showtime));

        mockMvc.perform(get("/showtimes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.showtimeID").value(1));

        verify(showtimeRepository, times(1)).findById(1);
        verifyNoMoreInteractions(showtimeRepository);
    }
}
