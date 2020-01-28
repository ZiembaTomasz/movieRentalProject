package pl.tomasz.project.rental.rental.controller.thymeleaf;

import lombok.Data;

@Data
public class CalculatePriceDto {
    private Long movieId;
    private int days;
}
