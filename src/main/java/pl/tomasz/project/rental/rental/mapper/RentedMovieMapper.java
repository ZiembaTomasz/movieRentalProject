package pl.tomasz.project.rental.rental.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;
import pl.tomasz.project.rental.rental.domain.RentedMovies;
import pl.tomasz.project.rental.rental.domain.RentedMoviesDto;

@Data
@Component
public class RentedMovieMapper {
    public RentedMovies mapToRentedMovie(RentedMoviesDto rentedMoviesDto){
        return new RentedMovies(rentedMoviesDto.getMovieId(), rentedMoviesDto.getUserId(),
                rentedMoviesDto.getDateOfRent(), rentedMoviesDto.getReturnedDate());
    }
}
