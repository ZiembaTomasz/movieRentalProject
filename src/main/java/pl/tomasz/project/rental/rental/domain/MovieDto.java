package pl.tomasz.project.rental.rental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import pl.tomasz.project.rental.rental.interfaces.MovieType;

@AllArgsConstructor
@Data

public class MovieDto {
    private MovieType movieType;
    private int days;
}
