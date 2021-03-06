package pl.tomasz.project.rental.rental.service;

import org.junit.Before;
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

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {
    private ArrayList<UserRating> userRatings = new ArrayList<>();
    private Movie movie;
    private MovieService movieService;
    @Mock
    UserRepository userRepository;
    @Mock
    MovieRepository movieRepository;
    @Mock
    RentedMoviesRepository rentedMoviesRepository;
    @Mock
    RentedMovieService rentedMovieService;
    private MovieMapper movieMapper = new MovieMapper();
    @Before
    public void createMovieServiceObject(){
        movieService = new MovieService(movieMapper, movieRepository, userRepository,
                rentedMoviesRepository, rentedMovieService);
    }
    @Before
    public void createMovieObject(){
         movie = new Movie(1L, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
    }
    @Test
    public void priceOfNewMovieTest(){
        //Given
        MovieType movieTypeNew = MovieType.NEW_MOVIE;
        int priceOfNew1;
        int priceOfNew2;
        //When
        priceOfNew1 = movieService.priceOfMovie(movieTypeNew, 3);
        priceOfNew2 = movieService.priceOfMovie(movieTypeNew, 5);
        //Then
        assertEquals(priceOfNew1, 20);
        assertEquals(priceOfNew2, 40);
    }
    @Test
    public void priceOfBasicMovieTest(){
        //Given
        MovieType movieTypeBasic = MovieType.BASIC_MOVIE;
        int priceOfBasic1;
        int priceOfBasic2;
        //When
        priceOfBasic1 = movieService.priceOfMovie(movieTypeBasic, 3);
        priceOfBasic2 = movieService.priceOfMovie(movieTypeBasic, 5);
        //Then
        assertEquals(priceOfBasic1, 15);
        assertEquals(priceOfBasic2, 30);
    }
    @Test
    public void priceOfOldMovieTest(){
        //Given
        MovieType movieTypeOld = MovieType.OLD_MOVIE;
        int priceOfOld1;
        int priceOfOld2;
        //When
        priceOfOld1 = movieService.priceOfMovie(movieTypeOld, 3);
        priceOfOld2 = movieService.priceOfMovie(movieTypeOld, 5);
        //Then
        assertEquals(priceOfOld1, 10);
        assertEquals(priceOfOld2, 20);
    }
    @Test
    public void shouldRentMovie() {
        //Given
        User user = new User(1L,"Jack", "Sparrow", 1);
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        //|When
        String text = movieService.rentMovie(1L, 1L);
        //Then
        assertEquals("Jack Sparrow rented Mohawk", text);
    }
    @Test
    public void shouldGetMovieById(){
        //Given
        when(movieRepository.findById(1l)).thenReturn(Optional.of(movie));
        //When
        MovieDto result = movieService.getMovieById(1L);
        //Then
        assertThat(result.getId(), is(1L));

    }
    @Test
    public void shouldFindMovieByWord(){
        //Given
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        when(movieRepository.findByTitleLike("Moh%")).thenReturn(movies);
        //When
        List<MovieDto>quantity = movieService.findMovieByWord("Moh");
        int quantityOfMovies = quantity.size();
        //Then
        assertEquals(1, quantityOfMovies );
    }
    @Test
    public void shouldCheckAgeRestriction(){
        //Given
        when(movieRepository.getOne(1L)).thenReturn(movie);
        //When
        boolean result = movieService.checkAgeRestriction(1L);
        //Then
        assertTrue(result);
    }
    @Test
    public void shouldGetRatingOfMovie(){
        //Given
        User user = new User(1L,"Jack", "Sparrow", 1);
        UserRating userRating = new UserRating(1L, 10, user, 1L);
        userRatings.add(userRating);
        Movie movie = new Movie(1L, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        //When
        double result = movieService.getRating(1L);
        //Then
        assertEquals(10, result, 0.1);
    }
    @Test
    public void shouldAddMovie(){
        //Given
        MovieDto movieDto = movieMapper.mapToMovieDto(movie);
        //When
        movieService.addMovie(movieDto);
        //Then
        verify(movieRepository, times(1)).save(movie);

    }
    @Test
    public void shouldUpdateMovie(){
        //Given
        MovieDto movieDto = new MovieDto(1L, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movieMapper.mapToMovie(movieDto)));
        //When
        MovieDto resultMovie = movieService.updateMovie(movieDto);
        //Then
        assertEquals(movieDto, resultMovie);
    }
    @Test
    public void shouldDeleteMovie(){
        //Given
        MovieDto movieDto = movieMapper.mapToMovieDto(movie);
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movieMapper.mapToMovie(movieDto)));
        //When
        movieService.deleteMovie(1L);
        //Then
        verify(movieRepository, times(1)).delete(movie);

    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenNullMovieId(){
        //Given
        MovieDto movieDto = new MovieDto(null, "Mohawk", MovieType.NEW_MOVIE, "action",
                2018, true, userRatings);
        //When
        movieService.updateMovie(movieDto);
    }
    @Test
    public void shouldGetMovieByYear(){
        //Given
        List<Movie>movieList = new ArrayList<>();
        movieList.add(movie);
        when(movieRepository.findAll()).thenReturn(movieList);
        //When
        List<MovieDto>myChoosenYearList = movieService.getMovieByYear(2018);
        //Then
        assertEquals(1, myChoosenYearList.size());
    }
    @Test
    public void shouldGetMoviesByCategorie(){
        //Given
        Movie movie1 = new Movie(2L, "Mohcak", MovieType.OLD_MOVIE, "horror",
                1990, false, userRatings);
        List<Movie>movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        when(movieRepository.findAll()).thenReturn(movieList);
        //When
        List<MovieDto>myChoosenCategorie = movieService.getMoviesByCategorie("horror");
        //Then
        assertEquals(1, myChoosenCategorie.size());
    }
    @Test
    public void shouldReturnMovie(){
        //Given
        User user = new User(1L, "Jack", "Sparrow", 1);
        when(movieRepository.getOne(1L)).thenReturn(movie);
        when(userRepository.getOne(1L)).thenReturn(user);
        //When
        String text = movieService.returnMovie(1L,1L );
        //Then
        assertEquals(text, "Jack Sparrow returned Mohawk");
    }
    @Test
    public void shouldGetAllMovies(){
        //Given
        List<Movie>movies = new ArrayList<>();
        movies.add(movie);
        when(movieRepository.findAll()).thenReturn(movies);
        //When
        List<MovieDto>myMovies = movieService.getAllMovies();
        //Then
        assertEquals(1, myMovies.size());
    }
    @Test
    public void getMovieByMovieType(){
        //Given
        Movie movie1 = new Movie(2L, "Mohcak", MovieType.OLD_MOVIE, "horror",
                1990, false, userRatings);
        List<Movie>movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        when(movieRepository.findAll()).thenReturn(movieList);
        //When
        List<MovieDto>movieDtoList = movieService.getMovieByMovieType(MovieType.NEW_MOVIE);
        //Then
        assertEquals(1, movieDtoList.size());
    }
}
