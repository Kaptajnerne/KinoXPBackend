package com.example.kinoxpbackend;

import com.example.kinoxpbackend.model.Theater;
import com.example.kinoxpbackend.repository.TheaterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TheaterRepositoryTest {

    @Autowired
    private TheaterRepository theaterRepository;

    @Test
    public void testCreateTheater() {

        Theater theater = new Theater();
        theater.setSeatsPrLine(10);
        theater.setNumberOfLines(5);

        theaterRepository.save(theater);

        Theater savedTheater = theaterRepository.findById(theater.getTheaterID()).orElse(null);

        assertNotNull(savedTheater);

        assertEquals(10, savedTheater.getSeatsPrLine());
        assertEquals(5, savedTheater.getNumberOfLines());
    }

    @Test
    public void testFindTheaterByTheaterID() {

        Theater theater = new Theater();
        theater.setSeatsPrLine(10);
        theater.setNumberOfLines(5);

        theaterRepository.save(theater);

        Theater retrievedTheater = theaterRepository.findTheaterByTheaterID(theater.getTheaterID());

        assertNotNull(retrievedTheater);

        assertEquals(10, retrievedTheater.getSeatsPrLine());
        assertEquals(5, retrievedTheater.getNumberOfLines());
    }

}
