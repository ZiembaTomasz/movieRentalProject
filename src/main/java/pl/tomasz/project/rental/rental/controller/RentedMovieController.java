package pl.tomasz.project.rental.rental.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.tomasz.project.rental.rental.domain.RentedMovieDto;
import pl.tomasz.project.rental.rental.service.RentedMovieService;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Data
@RequestMapping("/rentedMovies")
public class RentedMovieController {
    private RentedMovieService rentedMovieService;

    @RequestMapping(method = RequestMethod.GET, value = "getRentedList")
    public List<RentedMovieDto> getRentedMovies(){
        return rentedMovieService.getAllRentedMovies();
    }
    @RequestMapping(method = RequestMethod.GET, value = "getNumberOfRentedMovies")
    public int getNumberOfRentedMoviesByUser(@RequestParam Long userId){
       return rentedMovieService.countRentedMoviesByUserId(userId);
    }

}
