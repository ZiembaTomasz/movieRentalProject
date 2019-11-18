package pl.tomasz.project.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tomasz.project.rental.rental.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
