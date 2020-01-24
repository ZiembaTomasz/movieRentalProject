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

    @GetMapping
    public List<MovieDto> getMovies() {
        return movieService.getAllMovies();
    }
    @GetMapping (value = "/{movieId}")
    public MovieDto getMovie(@PathVariable Long movieId) throws MovieNotFoundException{
        return movieService.getMovieById(movieId);
    }

    @GetMapping (value = "countMoviePrice")
    public int countMoviePrice(MovieType movieType, int days) {
        return movieService.priceOfMovie(movieType, days);
    }

    @PostMapping(value = "/{movieId}/rent/{userId}")
    public String rentMovie(@PathVariable Long movieId, @PathVariable Long userId) {
        return movieService.rentMovie(movieId, userId);
    }

    @PutMapping(value = "/movie/{movieId}/return/{userID}")
    public void returnMovie(@PathVariable Long movieId, Long userId) {
        movieService.returnMovie(movieId, userId);
    }

    @GetMapping(value = "getMoviesByCategories")
    public List<MovieDto> getMoviesByCategories(@RequestParam String category) {
        return movieService.getMoviesByCategorie(category);
    }

    @GetMapping(value = "getMoviesByYear")
    public List<MovieDto> getMoviesByYear(@RequestParam int year) {
        return movieService.getMovieByYear(year);
    }
    @PutMapping(value = "addMovie")
    public void addMovie(@RequestBody MovieDto movieDto){
        movieService.addMovie(movieDto);
    }

    @PutMapping(value = "updateMovie")
    public MovieDto updateMovie(@RequestBody MovieDto movieDto) throws MovieNotFoundException{
        return movieService.updateMovie(movieDto);
    }

    @GetMapping(value = "getAgeRestriction")
    public boolean getAgeRestriction(@RequestParam Long movieId) {
        return movieService.checkAgeRestriction(movieId);
    }

    @GetMapping(value = "searchMoviesByWord")
    public List<MovieDto> searchMoviesByWord(@RequestParam String word) {
        return movieService.findMovieByWord(word);
    }
    @DeleteMapping(value = "deleteMovie")
    public void deleteMovie(@RequestParam  Long movieId){
        movieService.deleteMovie(movieId);
    }
    @GetMapping(value = "getRatingOfMovie")
    public double getRating(Long movieId){
        return movieService.getRating(movieId);
    }
}
