package pl.tomasz.project.rental.rental.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String firstName;

    @Column
    @NotNull
    private String secondName;

    @Column
    @NotNull
    private Date birthDate;
}
