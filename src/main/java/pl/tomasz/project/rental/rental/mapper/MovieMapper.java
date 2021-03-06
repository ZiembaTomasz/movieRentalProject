package pl.tomasz.project.rental.rental.mapper;

import org.springframework.stereotype.Component;
import pl.tomasz.project.rental.rental.domain.Movie;
import pl.tomasz.project.rental.rental.domain.MovieDto;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class MovieMapper {
    public Movie mapToMovie(MovieDto movieDto) {
        return new Movie(movieDto.getId(), movieDto.getTitle(), movieDto.getMovieType(),
                movieDto.getCategory(), movieDto.getYearOfProduction(), movieDto.isUnder18(), movieDto.getUserRatings());
    }

    public MovieDto mapToMovieDto(Movie movie) {
        return new MovieDto(movie.getId(), movie.getTitle(), movie.getMovieType(),
                movie.getCategory(), movie.getYearOfProduction(), movie.isUnder18(), movie.getUserRating());
    }

    public List<MovieDto> mapToMovieDtoList(List<Movie> movieList) {
        return movieList.stream()
                .map(this::mapToMovieDto)
                .collect(Collectors.toList());
    }
}
