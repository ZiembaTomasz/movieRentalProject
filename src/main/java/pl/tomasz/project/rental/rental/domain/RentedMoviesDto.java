package pl.tomasz.project.rental.rental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
public class RentedMoviesDto {
    private Long userId;
    private Long movieId;
    private LocalDateTime dateOfRent;
    private LocalDateTime returnedDate;
}
