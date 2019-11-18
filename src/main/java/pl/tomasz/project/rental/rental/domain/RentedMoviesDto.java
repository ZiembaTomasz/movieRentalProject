package pl.tomasz.project.rental.rental.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class RentedMoviesDto {
    private Long userId;
    private Long movieId;
    LocalDateTime dateOfRent;
   LocalDateTime returnedDate;
}
