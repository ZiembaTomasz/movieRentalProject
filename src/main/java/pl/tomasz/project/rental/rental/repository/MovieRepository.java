package pl.tomasz.project.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tomasz.project.rental.rental.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
