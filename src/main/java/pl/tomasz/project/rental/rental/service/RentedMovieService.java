package pl.tomasz.project.rental.rental.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import pl.tomasz.project.rental.rental.domain.RentedMovies;
import pl.tomasz.project.rental.rental.domain.RentedMoviesDto;
import pl.tomasz.project.rental.rental.domain.User;
import pl.tomasz.project.rental.rental.mapper.RentedMovieMapper;
import pl.tomasz.project.rental.rental.repository.RentedMoviesRepository;
import pl.tomasz.project.rental.rental.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@AllArgsConstructor
public class RentedMovieService {
    private RentedMovieMapper rentedMovieMapper;
    private RentedMoviesRepository rentedMoviesRepository;
    private UserRepository userRepository;

    public List<RentedMoviesDto> getAllRentedMovies(){
       return rentedMovieMapper.mapToRentedMovieList(rentedMoviesRepository.findAll());
    }
    public int countRentedMovies(Long userId){
        int numberOfRentedMovies = 0;
        User user = userRepository.getOne(userId);
        List<RentedMovies>rentedMoviesList = new ArrayList<>();
        if (rentedMoviesList.contains(user)){
            numberOfRentedMovies = rentedMoviesList.size();
        }
        return numberOfRentedMovies;

    }
}
