package pl.tomasz.project.rental.rental.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;
import pl.tomasz.project.rental.rental.domain.RentedMovies;
import pl.tomasz.project.rental.rental.domain.RentedMoviesDto;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class RentedMovieMapper {
    public RentedMovies mapToRentedMovie(RentedMoviesDto rentedMoviesDto){
        return new RentedMovies(rentedMoviesDto.getId(), rentedMoviesDto.getMovieId(), rentedMoviesDto.getUserId(),
                rentedMoviesDto.getDateOfRent(), rentedMoviesDto.getReturnedDate());
    }
    public RentedMoviesDto mapToRentedMovieDto(RentedMovies rentedMovies){
        return new RentedMoviesDto(rentedMovies.getId(), rentedMovies.getUserId(), rentedMovies.getMovieId(),
                rentedMovies.getDateOfRent(), rentedMovies.getReturnedDate());
    }
    public List<RentedMoviesDto> mapToRentedMovieList(List<RentedMovies>rentedMoviesList){
        return rentedMoviesList.stream()
                .map(t -> mapToRentedMovieDto(t))
                .collect(Collectors.toList());
    }
}
