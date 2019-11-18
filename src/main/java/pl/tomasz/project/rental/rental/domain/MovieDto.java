package pl.tomasz.project.rental.rental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tomasz.project.rental.rental.interfaces.MovieType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {
    private String title;
    private MovieType movieType;
    private int days;
}
