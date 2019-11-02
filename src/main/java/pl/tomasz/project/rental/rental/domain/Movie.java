package pl.tomasz.project.rental.rental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tomasz.project.rental.rental.interfaces.MovieType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private MovieType movieType;
    private int days;

}
