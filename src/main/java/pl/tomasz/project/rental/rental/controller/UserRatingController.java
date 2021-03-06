package pl.tomasz.project.rental.rental.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.tomasz.project.rental.rental.domain.UserRatingDto;
import pl.tomasz.project.rental.rental.service.UserRatingService;

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
    @PutMapping(value = "addUserRating")
    public void addUserRating(@RequestBody UserRatingDto userRatingDto){

    }
    @PostMapping(value = "updateUserRating")
    public UserRatingDto updateUserRating(@RequestBody UserRatingDto userRatingDto){
        return userRatingService.updateUserRating(userRatingDto);
    }
    @DeleteMapping(value = "deleteUserRating")
    public void deleteUserRating(@RequestParam Long userRatingId){
        userRatingService.deleteUserRating(userRatingId);
    }
}
