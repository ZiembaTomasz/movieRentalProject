package pl.tomasz.project.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tomasz.project.rental.rental.domain.UserRating;

public interface UserRatingRepository extends JpaRepository<UserRating, Long> {
}
