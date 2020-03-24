package pl.tomasz.project.rental.rental.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;
import pl.tomasz.project.rental.rental.domain.RentedMovie;
import pl.tomasz.project.rental.rental.domain.RentedMovieDto;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class RentedMovieMapper {
    public RentedMovie mapToRentedMovie(RentedMovieDto rentedMovieDto){
        return new RentedMovie(rentedMovieDto.getId(), rentedMovieDto.getMovieId(), rentedMovieDto.getUserId(),
                rentedMovieDto.getDateOfRent(), rentedMovieDto.getReturnedDate());
    }
    public RentedMovieDto mapToRentedMovieDto(RentedMovie rentedMovie){
        return new RentedMovieDto(rentedMovie.getId(), rentedMovie.getUserId(), rentedMovie.getMovieId(),
                rentedMovie.getDateOfRent(), rentedMovie.getReturnedDate());
    }
    public List<RentedMovieDto> mapToRentedMovieList(List<RentedMovie> rentedMovieList){
        return rentedMovieList.stream()
                .map(t -> mapToRentedMovieDto(t))
                .collect(Collectors.toList());
    }
}
