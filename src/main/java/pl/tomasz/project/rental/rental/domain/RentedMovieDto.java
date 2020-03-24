package pl.tomasz.project.rental.rental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RentedMovieDto {
    private Long id;
    private Long userId;
    private Long movieId;
    private LocalDate dateOfRent;
    private LocalDate returnedDate;
}
