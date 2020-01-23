package pl.tomasz.project.rental.rental.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.tomasz.project.rental.rental.domain.MovieDto;
import pl.tomasz.project.rental.rental.interfaces.MovieType;
import pl.tomasz.project.rental.rental.service.MovieService;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;

    @RequestMapping(method = RequestMethod.GET)
    public List<MovieDto> getMovies() {
        return movieService.getAllMovies();
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{movieId}")
    public MovieDto getMovie(@PathVariable Long movieId) throws MovieNotFoundException{
        return movieService.getMovieById(movieId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "countMoviePrice")
    public int countMoviePrice(MovieType movieType, int days) {
        return movieService.priceOfMovie(movieType, days);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{movieId}/rent/{userId}")
    public String rentMovie(@PathVariable Long movieId, @PathVariable Long userId) {
        return movieService.rentMovie(movieId, userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/movie/{movieId}/return/{userID}")
    public void returnMovie(@PathVariable Long movieId, Long userId) {
        movieService.returnMovie(movieId, userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMoviesByCategories")
    public List<MovieDto> getMoviesByCategories(@RequestParam String category) {
        return movieService.getMoviesByCategorie(category);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMoviesByYear")
    public List<MovieDto> getMoviesByYear(@RequestParam int year) {
        return movieService.getMovieByYear(year);
    }
    @RequestMapping(method = RequestMethod.POST, value = "addMovie")
    public void addMovie(@RequestBody MovieDto movieDto){
        movieService.addMovie(movieDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateMovie")
    public MovieDto updateMovie(@RequestBody MovieDto movieDto) throws MovieNotFoundException{
        return movieService.updateMovie(movieDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAgeRestriction")
    public boolean getAgeRestriction(@RequestParam Long movieId) {
        return movieService.checkAgeRestriction(movieId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "searchMoviesByWord")
    public List<MovieDto> searchMoviesByWord(@RequestParam String word) {
        return movieService.findMovieByWord(word);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteMovie")
    public void deleteMovie(@RequestParam  Long movieId){
        movieService.deleteMovie(movieId);
    }
    @RequestMapping(method = RequestMethod.GET, value = "getRatingOfMovie")
    public double getRating(Long movieId){
        return movieService.getRating(movieId);
    }
}
