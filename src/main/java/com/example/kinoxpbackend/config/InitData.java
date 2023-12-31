package com.example.kinoxpbackend.config;

import com.example.kinoxpbackend.model.*;
import com.example.kinoxpbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ShowtimeRepository showTimeRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    SeatShowtimeRepository seatShowTimeRepository;

    @Autowired
    AdminRepository adminRepository;

    @Override
    public void run(String... args) throws Exception {

        //Admin
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("x");
        adminRepository.save(admin);

        // Theaters
        Theater smallTheater = new Theater();
        smallTheater.setSeatsPrLine(12);
        smallTheater.setNumberOfLines(20);
        theaterRepository.save(smallTheater);

        Theater largeTheater = new Theater();
        largeTheater.setSeatsPrLine(16);
        largeTheater.setNumberOfLines(25);
        theaterRepository.save(largeTheater);

        //Movies
        Movie movie1 = new Movie();
        movie1.setTitle("Saw X");
        movie1.setDescription("Lots of gore");
        movie1.setAgeLimit(15);
        movie1.setGenre("Horror");
        movie1.setDuration(118);
        movie1.setMovieImageUrl("https://i0.wp.com/bloody-disgusting.com/wp-content/uploads/2023/07/saw-x-poster.jpg?resize=740%2C925&ssl=1");
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Morbius");
        movie2.setDescription("Great movie");
        movie2.setAgeLimit(13);
        movie2.setGenre("Action");
        movie2.setDuration(104);
        movie2.setMovieImageUrl("https://www.themoviedb.org/t/p/original/6JjfSchsU6daXk2AKX8EEBjO3Fm.jpg");
        movieRepository.save(movie2);

        Movie movie3 = new Movie();
        movie3.setTitle("Spider Man");
        movie3.setDescription("Spider movie");
        movie3.setAgeLimit(13);
        movie3.setGenre("Adventure and Action");
        movie3.setDuration(110);
        movie3.setMovieImageUrl("https://m.media-amazon.com/images/M/MV5BMjMwNDkxMTgzOF5BMl5BanBnXkFtZTgwNTkwNTQ3NjM@._V1_.jpg");
        movieRepository.save(movie3);

        Movie movie4 = new Movie();
        movie4.setTitle("Avatar");
        movie4.setDescription("Blue movie");
        movie4.setAgeLimit(10);
        movie4.setGenre("Adventure and action");
        movie4.setDuration(145);
        movie4.setMovieImageUrl("https://m.media-amazon.com/images/M/MV5BYjhiNjBlODctY2ZiOC00YjVlLWFlNzAtNTVhNzM1YjI1NzMxXkEyXkFqcGdeQXVyMjQxNTE1MDA@._V1");
        movieRepository.save(movie4);

        //ShowTime
        Showtime showtime1Movie1 = new Showtime();
        showtime1Movie1.setDate(LocalDate.now());
        showtime1Movie1.setTime(LocalTime.of(12, 0));
        showtime1Movie1.setTheater(smallTheater);
        showtime1Movie1.setMovie(movie1);
        showTimeRepository.save(showtime1Movie1);

        //Seats
        for (int line = 1; line <= smallTheater.getNumberOfLines(); line++) {
            for (int seat = 1; seat <= smallTheater.getSeatsPrLine(); seat++) {
                Seat s1 = new Seat();
                s1.setTheater(smallTheater);
                s1.setPrice(100);
                s1.setLine(line);
                s1.setSeat(seat);
                s1.setReserved(false);
                seatRepository.save(s1);

                //Associate Seat and Showtime SeatShowTime
                SeatShowtime seatShowTime = new SeatShowtime();
                seatShowTime.setSeat(s1);
                seatShowTime.setShowTime(showtime1Movie1);
                seatShowTime.setPrice(100);
                seatShowTime.setReserved(false);
                seatShowTimeRepository.save(seatShowTime);

            }
        }

        Showtime showtime2Movie1 = new Showtime();
        showtime2Movie1.setDate(LocalDate.now());
        showtime2Movie1.setTime(LocalTime.of(18, 0));
        showtime2Movie1.setTheater(largeTheater);
        showtime2Movie1.setMovie(movie1);
        showTimeRepository.save(showtime2Movie1);

        //Seats
        for (int line = 1; line <= largeTheater.getNumberOfLines(); line++) {
            for (int seat = 1; seat <= largeTheater.getSeatsPrLine(); seat++) {
                Seat s1 = new Seat();
                s1.setTheater(largeTheater);
                s1.setPrice(100);
                s1.setLine(line);
                s1.setSeat(seat);
                s1.setReserved(false);
                seatRepository.save(s1);

                //Associate Seat and Showtime SeatShowTime
                SeatShowtime seatShowTime = new SeatShowtime();
                seatShowTime.setSeat(s1);
                seatShowTime.setShowTime(showtime2Movie1);
                seatShowTime.setPrice(100);
                seatShowTime.setReserved(false);
                seatShowTimeRepository.save(seatShowTime);

            }
        }

        Showtime showtime3Movie1 = new Showtime();
        showtime3Movie1.setDate(LocalDate.now().plusDays(1));
        showtime3Movie1.setTime(LocalTime.of(12, 0));
        showtime3Movie1.setTheater(smallTheater);
        showtime3Movie1.setMovie(movie1);
        showTimeRepository.save(showtime3Movie1);


        Showtime showtime1Movie2 = new Showtime();
        showtime1Movie2.setDate(LocalDate.now().plusDays(1));
        showtime1Movie2.setTime(LocalTime.of(12, 0));
        showtime1Movie2.setTheater(smallTheater);
        showtime1Movie2.setMovie(movie1);
        showTimeRepository.save(showtime1Movie2);

        for (int line = 1; line <= smallTheater.getNumberOfLines(); line++) {
            for (int seat = 1; seat <= smallTheater.getSeatsPrLine(); seat++) {
                Seat s1 = new Seat();
                s1.setTheater(smallTheater);
                s1.setPrice(100);
                s1.setLine(line);
                s1.setSeat(seat);
                s1.setReserved(false);
                seatRepository.save(s1);

                //Associate Seat and Showtime SeatShowTime
                SeatShowtime seatShowTime = new SeatShowtime();
                seatShowTime.setSeat(s1);
                seatShowTime.setShowTime(showtime1Movie2);
                seatShowTime.setPrice(100);
                seatShowTime.setReserved(false);
                seatShowTimeRepository.save(seatShowTime);

            }
        }

        Showtime showtime2Movie2 = new Showtime();
        showtime2Movie2.setDate(LocalDate.now().plusDays(1));
        showtime2Movie2.setTime(LocalTime.of(15, 0));
        showtime2Movie2.setTheater(largeTheater);
        showtime2Movie2.setMovie(movie2);
        showTimeRepository.save(showtime2Movie2);

        for (int line = 1; line <= largeTheater.getNumberOfLines(); line++) {
            for (int seat = 1; seat <= largeTheater.getSeatsPrLine(); seat++) {
                Seat s1 = new Seat();
                s1.setTheater(largeTheater);
                s1.setPrice(100);
                s1.setLine(line);
                s1.setSeat(seat);
                s1.setReserved(false);
                seatRepository.save(s1);

                //Associate Seat and Showtime SeatShowTime
                SeatShowtime seatShowTime = new SeatShowtime();
                seatShowTime.setSeat(s1);
                seatShowTime.setShowTime(showtime2Movie2);
                seatShowTime.setPrice(100);
                seatShowTime.setReserved(false);
                seatShowTimeRepository.save(seatShowTime);

            }
        }

        Showtime showtime3Movie2 = new Showtime();
        showtime3Movie2.setDate(LocalDate.now().plusDays(1));
        showtime3Movie2.setTime(LocalTime.of(15, 0));
        showtime3Movie2.setTheater(largeTheater);
        showtime3Movie2.setMovie(movie2);
        showTimeRepository.save(showtime3Movie2);
        //No seats for this one


        Showtime showtime1Movie3 = new Showtime();
        showtime1Movie3.setDate(LocalDate.now().plusDays(2));
        showtime1Movie3.setTime(LocalTime.of(12, 0));
        showtime1Movie3.setTheater(largeTheater);
        showtime1Movie3.setMovie(movie3);
        showTimeRepository.save(showtime1Movie3);

        for (int line = 1; line <= largeTheater.getNumberOfLines(); line++) {
            for (int seat = 1; seat <= largeTheater.getSeatsPrLine(); seat++) {
                Seat s1 = new Seat();
                s1.setTheater(largeTheater);
                s1.setPrice(100);
                s1.setLine(line);
                s1.setSeat(seat);
                s1.setReserved(false);
                seatRepository.save(s1);

                //Associate Seat and Showtime SeatShowTime
                SeatShowtime seatShowTime = new SeatShowtime();
                seatShowTime.setSeat(s1);
                seatShowTime.setShowTime(showtime1Movie3);
                seatShowTime.setPrice(100);
                seatShowTime.setReserved(false);
                seatShowTimeRepository.save(seatShowTime);

            }
        }

        Showtime showtime2Movie3 = new Showtime();
        showtime2Movie3.setDate(LocalDate.now().plusDays(2));
        showtime2Movie3.setTime(LocalTime.of(15, 0));
        showtime2Movie3.setTheater(smallTheater);
        showtime2Movie3.setMovie(movie3);
        showTimeRepository.save(showtime2Movie3);

        for (int line = 1; line <= smallTheater.getNumberOfLines(); line++) {
            for (int seat = 1; seat <= smallTheater.getSeatsPrLine(); seat++) {
                Seat s1 = new Seat();
                s1.setTheater(smallTheater);
                s1.setPrice(100);
                s1.setLine(line);
                s1.setSeat(seat);
                s1.setReserved(false);
                seatRepository.save(s1);

                //Associate Seat and Showtime SeatShowTime
                SeatShowtime seatShowTime = new SeatShowtime();
                seatShowTime.setSeat(s1);
                seatShowTime.setShowTime(showtime2Movie3);
                seatShowTime.setPrice(100);
                seatShowTime.setReserved(false);
                seatShowTimeRepository.save(seatShowTime);

            }
        }

        Showtime showtime3Movie3 = new Showtime();
        showtime3Movie3.setDate(LocalDate.now().plusDays(2));
        showtime3Movie3.setTime(LocalTime.of(18, 0));
        showtime3Movie3.setTheater(smallTheater);
        showtime3Movie3.setMovie(movie3);
        showTimeRepository.save(showtime3Movie3);


        Showtime showtime1Movie4 = new Showtime();
        showtime1Movie4.setDate(LocalDate.now().plusDays(3));
        showtime1Movie4.setTime(LocalTime.of(12, 0));
        showtime1Movie4.setTheater(largeTheater);
        showtime1Movie4.setMovie(movie4);
        showTimeRepository.save(showtime1Movie4);

        for (int line = 1; line <= largeTheater.getNumberOfLines(); line++) {
            for (int seat = 1; seat <= largeTheater.getSeatsPrLine(); seat++) {
                Seat s1 = new Seat();
                s1.setTheater(largeTheater);
                s1.setPrice(100);
                s1.setLine(line);
                s1.setSeat(seat);
                s1.setReserved(false);
                seatRepository.save(s1);

                //Associate Seat and Showtime SeatShowTime
                SeatShowtime seatShowTime = new SeatShowtime();
                seatShowTime.setSeat(s1);
                seatShowTime.setShowTime(showtime1Movie4);
                seatShowTime.setPrice(100);
                seatShowTime.setReserved(false);
                seatShowTimeRepository.save(seatShowTime);

            }
        }

        Showtime showtime2Movie4 = new Showtime();
        showtime2Movie4.setDate(LocalDate.now().plusDays(3));
        showtime2Movie4.setTime(LocalTime.of(15, 0));
        showtime2Movie4.setTheater(smallTheater);
        showtime2Movie4.setMovie(movie4);
        showTimeRepository.save(showtime2Movie4);

        for (int line = 1; line <= smallTheater.getNumberOfLines(); line++) {
            for (int seat = 1; seat <= smallTheater.getSeatsPrLine(); seat++) {
                Seat s1 = new Seat();
                s1.setTheater(smallTheater);
                s1.setPrice(100);
                s1.setLine(line);
                s1.setSeat(seat);
                s1.setReserved(false);
                seatRepository.save(s1);

                //Associate Seat and Showtime SeatShowTime
                SeatShowtime seatShowTime = new SeatShowtime();
                seatShowTime.setSeat(s1);
                seatShowTime.setShowTime(showtime2Movie4);
                seatShowTime.setPrice(100);
                seatShowTime.setReserved(false);
                seatShowTimeRepository.save(seatShowTime);

            }
        }

        Showtime showtime3Movie4 = new Showtime();
        showtime3Movie4.setDate(LocalDate.now().plusDays(3));
        showtime3Movie4.setTime(LocalTime.of(18, 0));
        showtime3Movie4.setTheater(smallTheater);
        showtime3Movie4.setMovie(movie4);
        showTimeRepository.save(showtime3Movie4);

    }
}