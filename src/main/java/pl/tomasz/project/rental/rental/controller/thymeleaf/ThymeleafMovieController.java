package pl.tomasz.project.rental.rental.controller.thymeleaf;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomasz.project.rental.rental.service.MovieService;

import java.util.Map;

@Controller
@AllArgsConstructor
public class ThymeleafMovieController {

    private MovieService movieService;

    @RequestMapping("/")
    public String index(Map<String, Object> model){
        model.put("variable", "My Thymeleaf variable");
        model.put("movies", movieService.getAllMovies());
        return "index";
    }

}


