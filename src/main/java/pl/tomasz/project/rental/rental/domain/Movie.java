package pl.tomasz.project.rental.rental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.tomasz.project.rental.rental.interfaces.MovieType;

@Data
@AllArgsConstructor
public class Movie {
    private MovieType movieType;
    private int days;

}
