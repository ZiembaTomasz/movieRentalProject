package pl.tomasz.project.rental.rental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentedMovies {
    private Long userId;
    private Long movieId;
    LocalDateTime dateOfRent;
    LocalDateTime returnedDate;
}
