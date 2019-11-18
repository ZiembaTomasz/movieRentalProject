package pl.tomasz.project.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tomasz.project.rental.rental.domain.RentedMovies;

public interface RentedMoviesRepository extends JpaRepository<RentedMovies, Long> {
}
