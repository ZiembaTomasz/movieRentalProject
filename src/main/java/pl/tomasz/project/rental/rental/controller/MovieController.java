package pl.tomasz.project.rental.rental.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.tomasz.project.rental.rental.domain.Movie;
import pl.tomasz.project.rental.rental.domain.RentedMovies;
import pl.tomasz.project.rental.rental.domain.User;
import pl.tomasz.project.rental.rental.domain.MovieDto;
import pl.tomasz.project.rental.rental.interfaces.MovieType;
import pl.tomasz.project.rental.rental.service.MovieService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    @RequestMapping(method = RequestMethod.GET, value = "getMovies")
    public List<MovieDto> getMovies(){
        return movieService.getAllMovies();
    }

    @RequestMapping(method = RequestMethod.GET, value = "countMoviePrice")
    public int countMoviePrice(MovieType movieType, int days){
        return movieService.priceOfMovie(movieType, days);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/movie/{movieId}/rent/{userId}")
    public String rentMovie(Long movieId, Long userId, Date dateOfRent){
        return movieService.rentMovie(movieId, userId, dateOfRent);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/movie/{movieId}/return/{userID}")
    public String returnMovie(Long movieIdm, Long userId){
        return null;
    }
    @RequestMapping(method = RequestMethod.GET, value = "getRentedList")
    public List<RentedMovies>getRentedMovies(){
        return null;
    }
}
