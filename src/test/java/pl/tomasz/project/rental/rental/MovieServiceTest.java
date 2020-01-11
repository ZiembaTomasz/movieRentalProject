package pl.tomasz.project.rental.rental;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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
    public void shouldRentMovie() {
        MovieMapper movieMapperMock = mock(MovieMapper.class);
        MovieRepository movieRepositoryMock = mock(MovieRepository.class);
        UserRepository userRepositoryMock = mock(UserRepository.class);
        RentedMoviesRepository rentedMoviesRepositoryMock = mock(RentedMoviesRepository.class);
        ArrayList<UserRating> userRatings = new ArrayList<>();
        Date date = new GregorianCalendar(1988, 06, 15).getGregorianChange();
        User user = new User("Jack", "Sparrow", date);
        Movie movie = new Movie(1L, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
        when(movieRepositoryMock.getOne(1L)).thenReturn(movie);
        when(userRepositoryMock.getOne(1L)).thenReturn(user);

        Long userId = userRepositoryMock.findUserByDate(date);
        Long movieId = movieRepositoryMock.findMovieByMovieId(1L);
        MovieService movieService = new MovieService(movieMapperMock, movieRepositoryMock, userRepositoryMock,
                rentedMoviesRepositoryMock);
        String text = movieService.rentMovie(1l, 1l);
        assertEquals(text, "Jack Sparrow rented Mohawk");
    }
    @Test
    public void shouldFindMovieByWord(){
        MovieMapper movieMapperMock = mock(MovieMapper.class);
        MovieRepository movieRepositoryMock = mock(MovieRepository.class);
        UserRepository userRepositoryMock = mock(UserRepository.class);
        RentedMoviesRepository rentedMoviesRepositoryMock = mock(RentedMoviesRepository.class);
        List<UserRating> userRatings = new ArrayList<>();
        Movie movie = new Movie(1L, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
        Movie movie1 = new Movie(2L, "Mohcak", MovieType.OLD_MOVIE, "horror",
                1990, false, userRatings);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(movie1);
        when(movieRepositoryMock.findByNameLike("Moh")).thenReturn(movies);
        MovieService movieService = new MovieService(movieMapperMock, movieRepositoryMock, userRepositoryMock,
                rentedMoviesRepositoryMock);
        int quantityOfMovies = movies.size();
        assertEquals(2, quantityOfMovies );
    }
}
