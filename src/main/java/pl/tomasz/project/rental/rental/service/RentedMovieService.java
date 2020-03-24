package pl.tomasz.project.rental.rental.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import pl.tomasz.project.rental.rental.domain.RentedMovie;
import pl.tomasz.project.rental.rental.domain.RentedMovieDto;
import pl.tomasz.project.rental.rental.mapper.RentedMovieMapper;
import pl.tomasz.project.rental.rental.repository.RentedMoviesRepository;


import java.util.List;

@Service
@Data
@AllArgsConstructor
public class RentedMovieService {
    private RentedMovieMapper rentedMovieMapper;
    private RentedMoviesRepository rentedMoviesRepository;

    public List<RentedMovieDto> getAllRentedMovies(){
       return rentedMovieMapper.mapToRentedMovieList(rentedMoviesRepository.findAll());
    }
    public int countRentedMoviesByUser(Long userId){
        List<RentedMovie> rentedMovieList = rentedMoviesRepository.findMovieByUserId(userId);
        return rentedMovieList.size();
    }
}
