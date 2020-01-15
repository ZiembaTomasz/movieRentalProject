package pl.tomasz.project.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tomasz.project.rental.rental.domain.RentedMovies;

import java.util.List;

public interface RentedMoviesRepository extends JpaRepository<RentedMovies, Long> {
    List<RentedMovies> findMovieByUserId(Long userId);

}
