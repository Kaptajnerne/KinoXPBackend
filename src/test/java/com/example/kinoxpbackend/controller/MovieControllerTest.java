package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.model.Movie;
import com.example.kinoxpbackend.repository.MovieRepository;
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

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    void testFindAll() throws Exception {
        Movie movie = new Movie();
        movie.setMovieID(1);
        movie.setTitle("Test Movie");

        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie));

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].movieID").value(1))
                .andExpect(jsonPath("$[0].title").value("Test Movie"));

        verify(movieRepository, times(1)).findAll();
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void testFindById() throws Exception {
        Movie movie = new Movie();
        movie.setMovieID(1);
        movie.setTitle("Test Movie");

        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));

        mockMvc.perform(get("/movies/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.movieID").value(1))
                .andExpect(jsonPath("$.title").value("Test Movie"));

        verify(movieRepository, times(1)).findById(1);
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void testCreate() throws Exception {
        Movie movie = new Movie();
        movie.setMovieID(1);
        movie.setTitle("Test Movie");

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Movie\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.movieID").value(1))
                .andExpect(jsonPath("$.title").value("Test Movie"));

        verify(movieRepository, times(1)).save(any(Movie.class));
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void testUpdate() throws Exception {
        Movie movie = new Movie();
        movie.setMovieID(1);
        movie.setTitle("Updated Test Movie");

        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        mockMvc.perform(put("/movies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Test Movie\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.movieID").value(1))
                .andExpect(jsonPath("$.title").value("Updated Test Movie"));

        verify(movieRepository, times(1)).findById(1);
        verify(movieRepository, times(1)).save(any(Movie.class));
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void testDelete() throws Exception {
        Movie movie = new Movie();
        movie.setMovieID(1);
        movie.setTitle("Test Movie");

        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));

        mockMvc.perform(delete("/movies/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Movie deleted"));

        verify(movieRepository, times(1)).findById(1);
        verify(movieRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(movieRepository);
    }
}
