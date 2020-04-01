package pl.tomasz.project.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tomasz.project.rental.rental.domain.RentedMovie;

import java.util.List;

public interface RentedMoviesRepository extends JpaRepository<RentedMovie, Long> {
    List<RentedMovie> findMovieByUserId(Long userId);
    List<RentedMovie> findMovieByMovieId(Long movieId);
}
