package pl.tomasz.project.rental.rental.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.tomasz.project.rental.rental.domain.RentedMoviesDto;
import pl.tomasz.project.rental.rental.domain.User;
import pl.tomasz.project.rental.rental.service.RentedMovieService;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Data
@RequestMapping
public class RentedMovieController {
    private RentedMovieService rentedMovieService;

    @RequestMapping(method = RequestMethod.GET, value = "getRentedList")
    public List<RentedMoviesDto> getRentedMovies(){
        return rentedMovieService.getAllRentedMovies();
    }
    @RequestMapping(method = RequestMethod.GET, value = "getNumberOfRentedMovies")
    public int getNumberOfRentedMovies(Long userId){
       return rentedMovieService.countRentedMovies(userId);
    }
    @RequestMapping(method = RequestMethod.GET, value = "getRatingOfMovie")
    public double getRating(Long movieId){
        return 0;
    }
}
