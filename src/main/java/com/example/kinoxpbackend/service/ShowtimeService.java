package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.SeatShowtimeDTO;
import com.example.kinoxpbackend.model.*;
import com.example.kinoxpbackend.repository.SeatRepository;
import com.example.kinoxpbackend.repository.SeatShowtimeRepository;
import com.example.kinoxpbackend.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatShowtimeRepository seatShowtimeRepository;

    public SeatShowtimeDTO createShowtime(Showtime showtime, Theater theater, Movie movie) {
        //ShowTime
        Showtime st1 = new Showtime();
        st1.setDate(showtime.getDate());
        st1.setTime(showtime.getTime());
        st1.setTheater(theater);
        st1.setMovie(movie);
        showtimeRepository.save(st1);

        //Seats
        List<Seat> seats = new ArrayList<>();
        for (int line = 1; line <= theater.getNumberOfLines(); line++) {
            for (int seat = 1; seat <= theater.getSeatsPrLine(); seat++) {
                Seat s1 = new Seat();
                s1.setTheater(theater);
                s1.setPrice(100);
                s1.setLine(line);
                s1.setSeat(seat);
                s1.setReserved(false);
                seatRepository.save(s1);

                //Associate Seat and Showtime
                SeatShowtime seatShowTime = new SeatShowtime();
                seatShowTime.setSeat(s1);
                seatShowTime.setShowTime(st1);
                seatShowTime.setPrice(100);
                seatShowTime.setReserved(false);
                seatShowtimeRepository.save(seatShowTime);

                seats.add(s1);
            }
        }
        //DTO to combine the data
        SeatShowtimeDTO seatShowtimeDTO = new SeatShowtimeDTO();
        seatShowtimeDTO.setShowtime(st1);
        seatShowtimeDTO.setSeats(seats);

        return seatShowtimeDTO;
    }
}

