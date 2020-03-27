package pl.tomasz.project.rental.rental.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.tomasz.project.rental.rental.domain.UserDto;
import pl.tomasz.project.rental.rental.exception.UserNotFoundException;
import pl.tomasz.project.rental.rental.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PostMapping(value = "addUser")
    public void addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
    }

    @GetMapping
    public List<UserDto>getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping(value = "/{userId}")
    public UserDto getUser(@PathVariable Long userId)throws UserNotFoundException {
        return userService.getUserById(userId);
    }
    @DeleteMapping(value = "deleteUser")
    public void deleteMovie(@RequestParam Long movieId){
        userService.deleteUser(movieId);
    }
    @GetMapping(value = "/{accountNumber}")
    public UserDto getUserByAccountNumber(@PathVariable int accountNumber){
        return userService.getUserByAccountNumber(accountNumber);
    }
}
