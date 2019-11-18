package pl.tomasz.project.rental.rental.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import pl.tomasz.project.rental.rental.domain.Movie;
import pl.tomasz.project.rental.rental.domain.MovieDto;
import pl.tomasz.project.rental.rental.domain.RentedMovies;
import pl.tomasz.project.rental.rental.domain.User;
import pl.tomasz.project.rental.rental.interfaces.MovieType;
import pl.tomasz.project.rental.rental.mapper.MovieMapper;
import pl.tomasz.project.rental.rental.repository.MovieRepository;
import pl.tomasz.project.rental.rental.repository.RentedMoviesRepository;
import pl.tomasz.project.rental.rental.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Getter
@AllArgsConstructor
public class MovieService {
    MovieMapper movieMapper;
    MovieRepository movieRepository;
    UserRepository userRepository;
    RentedMoviesRepository rentedMoviesRepository;


    public int priceOfMovie(MovieType movieType, int days) {
        if (movieType == MovieType.NEW_MOVIE && days <= 3) {
            return 20;
        }
        if (movieType == MovieType.NEW_MOVIE && days > 3) {
            return 20 * (days - 3);
        }
        if (movieType == MovieType.BASIC_MOVIE && days <= 3) {
            return 15;
        }
        if (movieType == MovieType.BASIC_MOVIE && days > 3) {
            return 15 * (days - 3);
        }

        if (movieType == MovieType.OLD_MOVIE && days <= 3) {
            return 10;
        }
        if (movieType == MovieType.OLD_MOVIE && days > 3) {
            return 10 * (days - 3);
        }
        return 0;
    }

    public List<MovieDto> getAllMovies() {
        return movieMapper.mapToMovieDtoList(movieRepository.findAll());
    }

    public String rentMovie(Long movieId, Long userId) {

        User user = userRepository.getOne(userId);
        Movie movie = movieRepository.getOne(movieId);
        RentedMovies rentedMovies = new RentedMovies();
        if (rentedMovies.getMovieId().equals(movieId) && rentedMovies.getUserId().equals(userId))
            rentedMovies.setDateOfRent(LocalDateTime.now());
        rentedMoviesRepository.save(rentedMovies);
        return user.getFirstName() + user.getSecondName() + "rented" + movie.getTitle();
    }
}

