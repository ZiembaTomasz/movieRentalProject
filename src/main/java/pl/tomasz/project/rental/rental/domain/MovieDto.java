package pl.tomasz.project.rental.rental.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tomasz.project.rental.rental.interfaces.MovieType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {
    private Long id;
    private String title;
    private MovieType movieType;
    private String category;
    private int yearOfProduction;
    private List<UserRating>userRatings;
}
