package com.example.kinoxpbackend;

import com.example.kinoxpbackend.model.Movie;
import com.example.kinoxpbackend.repository.MovieRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MovieRepositoryTest {

    private MovieRepository movieRepository;
    private Movie testMovie;

    @BeforeAll
    public static void setUpBeforeClass() {
    }

    @BeforeEach
    public void setUp() {
        testMovie = new Movie();
        testMovie.setTitle("Test Movie");
        testMovie.setDescription("Test Description");
        testMovie.setAgeLimit(18);
        testMovie.setGenre("Test Genre");
        testMovie.setDuration(120);
        testMovie.setMovieImageUrl("test-image-url");
    }

    @Autowired
    public MovieRepositoryTest(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Test
    public void testGetAllMovies() {
        Movie movie1 = new Movie();
        movie1.setTitle("Movie 1");
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Movie 2");
        movieRepository.save(movie2);

        List<Movie> movies = movieRepository.findAll();

        assertNotNull(movies);
        assertEquals(2, movies.size());
    }

    @Test
    public void testMovieCreation() {
        movieRepository.save(testMovie);

        Movie savedMovie = movieRepository.findById(testMovie.getMovieID()).orElse(null);
        assertNotNull(savedMovie);
        assertEquals(testMovie.getTitle(), savedMovie.getTitle());
    }

    @Test
    public void testDeleteMovie() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movieRepository.save(movie);

        movieRepository.deleteById(movie.getMovieID());

        Movie deletedMovie = movieRepository.findById(movie.getMovieID()).orElse(null);

        assertNull(deletedMovie);
    }
    @Test
    public void testUpdateMovie() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movieRepository.save(movie);

        movie.setTitle("Updated Movie Title");
        movieRepository.save(movie);

        Movie updatedMovie = movieRepository.findById(movie.getMovieID()).orElse(null);

        assertNotNull(updatedMovie);
        assertEquals("Updated Movie Title", updatedMovie.getTitle());
    }

    @Test
    public void testFindMovieByMovieID() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movieRepository.save(movie);

        Movie retrievedMovie = movieRepository.findMovieByMovieID(movie.getMovieID());

        assertNotNull(retrievedMovie);

        assertEquals("Test Movie", retrievedMovie.getTitle());
    }
}
