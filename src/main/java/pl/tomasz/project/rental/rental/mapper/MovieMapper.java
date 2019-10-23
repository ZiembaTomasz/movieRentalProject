package pl.tomasz.project.rental.rental.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.tomasz.project.rental.rental.domain.Movie;
import pl.tomasz.project.rental.rental.domain.MovieDto;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MovieMapper {
    public Movie mapToMovie(MovieDto movieDto){
        return new Movie(movieDto.getMovieType(),
                movieDto.getDays());
    }
    public MovieDto mapToMovieDto(Movie movie){
        return new MovieDto(movie.getMovieType(),
                movie.getDays());
    }
    public List<MovieDto>mapToMovieDtoList(List<Movie>movieList){
        return movieList.stream()
                .map(t -> mapToMovieDto(t))
                .collect(Collectors.toList());
    }
}
