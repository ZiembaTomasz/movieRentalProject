package pl.tomasz.project.rental.rental;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import pl.tomasz.project.rental.rental.domain.Movie;
import pl.tomasz.project.rental.rental.domain.User;
import pl.tomasz.project.rental.rental.domain.UserRating;
import pl.tomasz.project.rental.rental.interfaces.MovieType;
import pl.tomasz.project.rental.rental.mapper.MovieMapper;
import pl.tomasz.project.rental.rental.repository.MovieRepository;
import pl.tomasz.project.rental.rental.repository.RentedMoviesRepository;
import pl.tomasz.project.rental.rental.repository.UserRepository;
import pl.tomasz.project.rental.rental.service.MovieService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class MovieServiceTest {

    MovieService movieService;
    UserRepository userRepository;
    MovieRepository movieRepository;

    @Test
    public void priceMovieTest(){
        //Given
        MovieMapper movieMapperMock = mock(MovieMapper.class);
        MovieRepository movieRepositoryMock = mock(MovieRepository.class);
        UserRepository userRepositoryMock = mock(UserRepository.class);
        RentedMoviesRepository rentedMoviesRepositoryMock = mock(RentedMoviesRepository.class);
        MovieType movieType = MovieType.NEW_MOVIE;
        MovieService movieService = new MovieService(movieMapperMock, movieRepositoryMock, userRepositoryMock,
                rentedMoviesRepositoryMock);
        int price;
        //When
        price = movieService.priceOfMovie(movieType, 3);
        //Then
        assertEquals(price, 20);

    }
    @Test
    public void shouldRentMovie(){
        ArrayList<UserRating>userRatings = new ArrayList<>();

        User user = new User("Jack", "Sparrow", new Date(15, 06, 1988));
        Movie movie = new Movie( 1L, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
        userRepository.save(user);
        movieRepository.save(movie);
        userRepository.getOne(1L).equals(user.)



}
