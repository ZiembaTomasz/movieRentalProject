package pl.tomasz.project.rental.rental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tomasz.project.rental.rental.interfaces.MovieType;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotNull
    private String title;
    @Column
    @NotNull
    private MovieType movieType;
    @Column
    @NotNull
    private String category;
    @Column
    @NotNull
    private int yearOfProduction;
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "movieId")
    private List<UserRating>userRating;

    public double ratingAverage() {
        return userRating.stream()
                .map(t -> t.getRate())
                .reduce(0, (sum, current) -> sum += current) * 1.0 / userRating.size();
    }
}
