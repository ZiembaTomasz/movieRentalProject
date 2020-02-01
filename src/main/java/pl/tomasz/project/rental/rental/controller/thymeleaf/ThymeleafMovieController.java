package pl.tomasz.project.rental.rental.controller.thymeleaf;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.tomasz.project.rental.rental.domain.MovieDto;
import pl.tomasz.project.rental.rental.domain.UserDto;
import pl.tomasz.project.rental.rental.interfaces.MovieType;
import pl.tomasz.project.rental.rental.service.MovieService;
import pl.tomasz.project.rental.rental.service.UserService;

import java.util.Map;

@Controller
@AllArgsConstructor
public class ThymeleafMovieController {

    private MovieService movieService;
    private UserService userService;

    @GetMapping("/")
    public String index(Map<String, Object> model){
        fillModel(model);
        return "index";
    }

    @PostMapping("/movie/delete/{movieId}")
    public String delete(@PathVariable("movieId") long movieId) {
        movieService.deleteMovie(movieId);
        return "redirect:/";
    }

    @PostMapping("/movie/add")
    public String addMovie(@ModelAttribute MovieDto movieDto) {
        movieService.addMovie(movieDto);
        return "redirect:/";
    }

    @GetMapping("/movie/calculate")
    public String calculate(@RequestParam("movieId") long movieId, @RequestParam("days") int days,
                            Map<String, Object>model) {
        MovieDto movieDto = movieService.getMovieById(movieId);
        MovieType movieType = movieDto.getMovieType();
        int price = movieService.priceOfMovie(movieType, days);
        model.put("price", price );
        fillModel(model);
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDto userDto, Map<String, Object>model) {
        userDto = userService.getUser(userDto.getId());
        model.put("user", userDto);
        fillModel(model);
        return "index";
    }

    private void fillModel(Map<String, Object> model) {
        model.put("movies", movieService.getAllMovies());
        model.put("newMovie", new MovieDto());
        model.put("calculator", new CalculatePriceDto());
        if (!model.containsKey("user")) {
            model.put("user", new UserDto());
        }
    }
    @PostMapping("/logout")
    public String logout(Map<String, Object>model){
        model.put("user", new UserDto());
        fillModel(model);
        return "index";
    }
}


