package pl.tomasz.project.rental.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tomasz.project.rental.rental.domain.User;

import java.util.Date;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByAccountNumber(int accountNumber);
    User findUserByLastName(String name);
}
