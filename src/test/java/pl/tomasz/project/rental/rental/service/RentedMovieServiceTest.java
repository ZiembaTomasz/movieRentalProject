package pl.tomasz.project.rental.rental.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.tomasz.project.rental.rental.domain.RentedMovie;
import pl.tomasz.project.rental.rental.domain.RentedMovieDto;
import pl.tomasz.project.rental.rental.mapper.RentedMovieMapper;
import pl.tomasz.project.rental.rental.repository.RentedMoviesRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RentedMovieServiceTest {
    @Mock
    RentedMoviesRepository rentedMoviesRepository;
    RentedMovieService rentedMovieService;
    RentedMovieMapper rentedMovieMapper = new RentedMovieMapper();
    @Before
    public void createRentedMovieServiceObject(){
        rentedMovieService = new RentedMovieService(rentedMovieMapper, rentedMoviesRepository);
    }
    @Test
    public void shouldGetAllRentedMovies(){
        //Given
        LocalDate dateOfRent = LocalDate.of(2014, Month.JANUARY, 1);
        LocalDate dateOfReturn = LocalDate.of(2014, Month.FEBRUARY, 3);
        RentedMovie rentedMovie = new RentedMovie(01L, 350L, 8L, dateOfRent, dateOfReturn);
        List<RentedMovie>rentedMovies = new ArrayList<>();
        rentedMovies.add(rentedMovie);
        when(rentedMoviesRepository.findAll()).thenReturn(rentedMovies);
        //When
        List<RentedMovieDto> rentedMovieDtos = rentedMovieService.getAllRentedMovies();
        //Then
        assertEquals(1, rentedMovieDtos.size());
    }
    @Test
    public void shouldCountRentedMoviesByUser(){
        //Given
        LocalDate dateOfRent = LocalDate.of(2014, Month.JANUARY, 1);
        LocalDate dateOfReturn = LocalDate.of(2014, Month.FEBRUARY, 3);
        RentedMovie rentedMovie = new RentedMovie(01L, 350L, 8L, dateOfRent, dateOfReturn);
        List<RentedMovie>rentedMovies = new ArrayList<>();
        rentedMovies.add(rentedMovie);
        when(rentedMoviesRepository.findMovieByUserId(350L)).thenReturn(rentedMovies);
        //When
        int result = rentedMovies.size();
        //Then
        assertEquals(1, rentedMovies.size());
    }
    @Test
    public void shouldCountRentedMoviesByMovieId(){
        //Then
        LocalDate dateOfRent = LocalDate.of(2014, Month.JANUARY, 1);
        LocalDate dateOfReturn = LocalDate.of(2014, Month.FEBRUARY, 3);
        RentedMovie rentedMovie = new RentedMovie(01L, 350L, 8L, dateOfRent, dateOfReturn);
        List<RentedMovie>rentedMovies = new ArrayList<>();
        rentedMovies.add(rentedMovie);
        when(rentedMoviesRepository.findMovieByMovieId(8L)).thenReturn(rentedMovies);
        //When
        int result = rentedMovies.size();
        assertEquals(1, result);

    }
}