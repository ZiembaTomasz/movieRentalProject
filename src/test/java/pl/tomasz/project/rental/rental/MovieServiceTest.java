package pl.tomasz.project.rental.rental;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import pl.tomasz.project.rental.rental.interfaces.MovieType;
import pl.tomasz.project.rental.rental.service.MovieService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class MovieServiceTest {

    MovieService movieService;

    @Test
    public void priceMovieTest(){
        //Given
        MovieType movieType = MovieType.NEW_MOVIE;
        MovieService movieServiceMock = mock(MovieService.class);
        int price;
        //When
        price = movieServiceMock.priceOfMovie(movieType, 3);
        //Then
        assertEquals(price, 20);

    }
}
