package pl.tomasz.project.rental.rental.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    public List<MovieDto> getMovies() {
        return movieService.getAllMovies();
    }

    @RequestMapping(method = RequestMethod.GET, value = "countMoviePrice")
    public int countMoviePrice(MovieType movieType, int days) {
        return movieService.priceOfMovie(movieType, days);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/movie/{movieId}/rent/{userId}")
    public String rentMovie(Long movieId, Long userId, Date dateOfRent) {
        return movieService.rentMovie(movieId, userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/movie/{movieId}/return/{userID}")
    public void returnMovie(Long movieId, Long userId) {
        movieService.returnMovie(movieId, userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMoviesByCategories")
    public List<MovieDto> getMoviesByCategories(String category) {
        return movieService.getMoviesByCategorie(category);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMoviesByYear")
    public List<MovieDto> getMoviesByYear(int year) {
        return movieService.getMovieByYear(year);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateMovie")
    public MovieDto updateMovie(@RequestBody MovieDto movieDto) {
        return movieService.updateMovie(movieDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAgeRestriction")
    public boolean getAgeRestriction(Long movieId) {
        return movieService.checkAgeRestriction(movieId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "searchMoviesByWord")
    public List<MovieDto> searchMoviesByWord(String word) {
        return movieService.findMovieByWord(word);
    }
}
