package pl.tomasz.project.rental.rental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private int accountNumber;
}
