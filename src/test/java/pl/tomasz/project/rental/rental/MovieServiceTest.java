package pl.tomasz.project.rental.rental;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pl.tomasz.project.rental.rental.domain.Movie;
import pl.tomasz.project.rental.rental.domain.MovieDto;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    MovieService movieService;
    @Mock
    UserRepository userRepository;
    @Mock
    MovieRepository movieRepository;
    @Mock
    RentedMoviesRepository rentedMoviesRepository;
    @Mock
    MovieMapper movieMapper;

    @Test
    public void priceMovieTest(){
        //Given

        MovieType movieType = MovieType.NEW_MOVIE;
        MovieService movieService = new MovieService(movieMapper, movieRepository, userRepository,
                rentedMoviesRepository);
        int price;
        //When
        price = movieService.priceOfMovie(movieType, 3);
        //Then
        assertEquals(price, 20);

    }
    @Test
    public void shouldRentMovie() {
        //Given
        ArrayList<UserRating> userRatings = new ArrayList<>();
        Date date = new GregorianCalendar(1988, 06, 15).getGregorianChange();
        User user = new User("Jack", "Sparrow", date);
        Movie movie = new Movie(1L, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
        when(movieRepository.getOne(1L)).thenReturn(movie);
        when(userRepository.getOne(1L)).thenReturn(user);

        Long userId = userRepository.findUserByDate(date);
        Long movieId = movieRepository.findMovieByMovieId(1L);
        MovieService movieService = new MovieService(movieMapper, movieRepository, userRepository,
                rentedMoviesRepository);
        //|When
        String text = movieService.rentMovie(1l, 1l);
        //Then
        assertEquals(text, "Jack Sparrow rented Mohawk");
    }
    @Test
    public void shouldFindMovieByWord(){
        //Given
        MovieMapper movieMapper = new MovieMapper();
        List<UserRating> userRatings = new ArrayList<>();
        Movie movie = new Movie(1L, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
        Movie movie1 = new Movie(2L, "Mohcak", MovieType.OLD_MOVIE, "horror",
                1990, false, userRatings);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(movie1);
        when(movieRepository.findByNameLike("Moh%")).thenReturn(movies);
        MovieService movieService = new MovieService(movieMapper, movieRepository, userRepository,
                rentedMoviesRepository);
        //When
        List<MovieDto>quantity = movieService.findMovieByWord("Moh");
        int quantityOfMovies = quantity.size();
        //Then
        assertEquals(2, quantityOfMovies );
    }
    @Test
    public void shouldCheckAgeRestriction(){
        //Given
        MovieMapper movieMapper = new MovieMapper();
        List<UserRating>userRatings = new ArrayList<>();
        Movie movie = new Movie(1L, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
        MovieService movieService = new MovieService(movieMapper, movieRepository, userRepository,
                rentedMoviesRepository);
        when(movieRepository.getOne(1L)).thenReturn(movie);
        //When
        boolean result = movieService.checkAgeRestriction(1L);
        //Then
        assertTrue(result);
    }
    @Test
    public void shouldUpdateMovie(){
        //Given
        MovieMapper movieMapper = new MovieMapper();
        List<UserRating>userRatings = new ArrayList<>();
        MovieDto movieDto = new MovieDto(1L, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
        MovieService movieService = new MovieService(movieMapper, movieRepository, userRepository,
                rentedMoviesRepository);
        //When
        MovieDto resultMovie = movieService.updateMovie(movieDto);
        //Then
        assertEquals(movieDto, resultMovie);

    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenNullMovieId(){
        //Given
        MovieMapper movieMapper = new MovieMapper();
        List<UserRating>userRatings = new ArrayList<>();
        MovieDto movieDto1 = new MovieDto(null, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
        MovieService movieService = new MovieService(movieMapper, movieRepository, userRepository,
                rentedMoviesRepository);
        //When
        movieService.updateMovie(movieDto1);
    }
    @Test
    public void shouldgetMovieByYear(){
        //Given
        MovieMapper movieMapper = new MovieMapper();
        List<MovieDto>movieDtoList = new ArrayList<>();
        List<UserRating>userRatings = new ArrayList<>();
        Movie movie = new Movie(1L, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
        Movie movie1 = new Movie(2L, "Mohcak", MovieType.OLD_MOVIE, "horror",
                1990, false, userRatings);
        List<Movie>movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        when(movieRepository.findAll()).thenReturn(movieList);
        MovieService movieService = new MovieService(movieMapper, movieRepository, userRepository,
                rentedMoviesRepository);
        //When
        List<MovieDto>myChoosenYearList = movieService.getMovieByYear(2018);
        //Then
        assertEquals(1, myChoosenYearList.size());
    }
    @Test
    public void shouldGetMoviesByCategorie(){
        //Given
        MovieMapper movieMapper = new MovieMapper();
        List<MovieDto>movieDtoList = new ArrayList<>();
        List<UserRating>userRatings = new ArrayList<>();
        Movie movie = new Movie(1L, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
        Movie movie1 = new Movie(2L, "Mohcak", MovieType.OLD_MOVIE, "horror",
                1990, false, userRatings);
        List<Movie>movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        when(movieRepository.findAll()).thenReturn(movieList);
        MovieService movieService = new MovieService(movieMapper, movieRepository, userRepository,
                rentedMoviesRepository);
        //When
        List<MovieDto>myChoosenCategorie = movieService.getMoviesByCategorie("horror");
        //Then
        assertEquals(1, myChoosenCategorie.size());
    }
}
