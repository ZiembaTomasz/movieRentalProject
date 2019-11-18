package pl.tomasz.project.rental.rental.domain;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private String firstName;
    private String secondName;
    private Date birthDate;
}
