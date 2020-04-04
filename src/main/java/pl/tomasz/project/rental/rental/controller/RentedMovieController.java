package pl.tomasz.project.rental.rental.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public List<RentedMovieDto> getRentedMovies(){
        return rentedMovieService.getAllRentedMovies();
    }
    @GetMapping
    public RentedMovieDto getRentedMovie(@RequestParam Long rentedMovieId){
        return rentedMovieService.getRentedMovie(rentedMovieId);
    }
    @GetMapping(value = "getNumberOfRentedMoviesByUserId")
    public int getNumberOfRentedMoviesByUser(@RequestParam Long userId){
       return rentedMovieService.countRentedMoviesByUserId(userId);
    }
    @GetMapping(value = "getNumberOfRentedMoviesByMovieId")
    public int getNumberOfRentedMoviesByMovie(@RequestParam Long movieId){
        return rentedMovieService.countRentedMoviesByMovieId(movieId);
    }
    @DeleteMapping(value = "delete")
    public void deleteRentedMovie(@RequestParam Long rentedMovieId){
        rentedMovieService.deleteRentedMovie(rentedMovieId);
    }


}
