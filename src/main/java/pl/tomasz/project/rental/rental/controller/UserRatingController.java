package pl.tomasz.project.rental.rental.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.tomasz.project.rental.rental.domain.UserRating;
import pl.tomasz.project.rental.rental.domain.UserRatingDto;
import pl.tomasz.project.rental.rental.service.UserRatingService;

import javax.persistence.PostUpdate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/userRating")
public class UserRatingController {
    private UserRatingService userRatingService;
    @GetMapping
    public List<UserRatingDto> getAllRatings(){
        return userRatingService.getAllUser();
    }
    @GetMapping(value = "getUser")
    public UserRatingDto getUserRating(@RequestParam Long userRatingId){
        return userRatingService.getUserRating(userRatingId);
    }
    @PostMapping(value = "updateUserRating")
    public UserRatingDto updateUserRting(@RequestBody UserRating userRating){
        return us
    }
}
