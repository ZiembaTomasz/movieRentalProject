package pl.tomasz.project.rental.rental.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.internal.util.Contracts;
import org.springframework.stereotype.Service;
import pl.tomasz.project.rental.rental.controller.MovieNotFoundException;
import pl.tomasz.project.rental.rental.domain.Movie;
import pl.tomasz.project.rental.rental.domain.MovieDto;
import pl.tomasz.project.rental.rental.domain.RentedMovies;
import pl.tomasz.project.rental.rental.domain.User;
import pl.tomasz.project.rental.rental.interfaces.MovieType;
import pl.tomasz.project.rental.rental.mapper.MovieMapper;
import pl.tomasz.project.rental.rental.repository.MovieRepository;
import pl.tomasz.project.rental.rental.repository.RentedMoviesRepository;
import pl.tomasz.project.rental.rental.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
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

    public List<MovieDto> getAllMovies(){
        return movieMapper.mapToMovieDtoList(movieRepository.findAll());
    }
    public MovieDto getMovieById(Long movieId)throws MovieNotFoundException{
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        MovieDto movieDto = movieMapper.mapToMovieDto(movie);
        return movieDto;
    }

    public String rentMovie(Long movieId, Long userId) {
        Contracts.assertNotNull(movieId, "Movie with that Id doesnt exist");
        Contracts.assertNotNull(userId, "User with that Id doesnt exist");
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        Contracts.assertNotNull(movie, "Movie doesnt exist");
        Contracts.assertNotNull(user,"User doesnt exist");
        RentedMovies rentedMovies = new RentedMovies();
        rentedMovies.setMovieId(movieId);
        rentedMovies.setUserId(userId);
        rentedMovies.setDateOfRent(LocalDateTime.now());
        rentedMoviesRepository.save(rentedMovies);
        return user.getFirstName() + " " + user.getSecondName() + " rented " + movie.getTitle();
    }

    public String returnMovie(Long movieId, Long userId) {
        User user = userRepository.getOne(userId);
        Movie movie = movieRepository.getOne(movieId);
        RentedMovies rentedMovies = new RentedMovies();
        rentedMovies.setMovieId(movieId);
        rentedMovies.setUserId(userId);
        rentedMovies.setReturnedDate(LocalDateTime.now());
        rentedMoviesRepository.save(rentedMovies);
        return user.getFirstName() + " " + user.getSecondName() + " returned " + movie.getTitle();
    }

    public List<MovieDto> getMoviesByCategorie(String category) {
        List<Movie> moviesList = movieRepository.findAll();
        List<MovieDto> movieDtoList = movieMapper.mapToMovieDtoList(moviesList);
        return movieDtoList.stream()
                .filter(t -> t.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public List<MovieDto> getMovieByYear(int year) {
        List<Movie> moviesList = movieRepository.findAll();
        List<MovieDto> movieDtoList = movieMapper.mapToMovieDtoList(moviesList);
        return movieDtoList.stream()
                .filter(t -> t.getYearOfProduction() == year)
                .collect(Collectors.toList());
    }
    public void addMovie(MovieDto movieDto){
        Contracts.assertNotNull(movieDto, "Cannot save empty Movie");
        Movie movie = movieMapper.mapToMovie(movieDto);
        movieRepository.save(movie);
    }
    public MovieDto updateMovie(MovieDto movieDto) throws MovieNotFoundException {
        Contracts.assertNotNull(movieDto.getId(), "Cannot update with no ID");
        Movie movie = movieMapper.mapToMovie(movieDto);
        Contracts.assertNotNull(movieRepository.findById(movie.getId()).orElseThrow(MovieNotFoundException::new));
        movieRepository.save(movie);
        return movieMapper.mapToMovieDto(movie);
    }

    public boolean checkAgeRestriction(Long movieId) {
        Movie movie = movieRepository.getOne(movieId);
        return movie.isUnder18();
    }

    public List<MovieDto> findMovieByWord(String word) {
        List<Movie> moviesList = movieRepository.findByTitleLike(word + "%");
        return movieMapper.mapToMovieDtoList(moviesList);
    }
    public void deleteMovie(Long movieId){
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        movieRepository.delete(movie);
    }
    public double getRating(Long movieId){
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        return movie.ratingAverage();
    }
}

