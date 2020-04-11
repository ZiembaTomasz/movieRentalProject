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

    @GetMapping
    public List<UserDto>getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping(value = "/{userId}")
    public UserDto getUser(@PathVariable Long userId)throws UserNotFoundException {
        return userService.getUserById(userId);
    }
    @PostMapping(value = "/addUser")
    public void addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
    }
    @PutMapping(value = "/updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }
    @DeleteMapping(value = "/deleteUser")
    public void deleteMovie(@RequestParam Long movieId){
        userService.deleteUser(movieId);
    }
    @GetMapping(value = "/user{accountNumber}")
    public UserDto getUserByAccountNumber(@PathVariable int accountNumber){
        return userService.getUserByAccountNumber(accountNumber);
    }
    @GetMapping(value = "/user{lastName}")
    public UserDto getUserByLastName(@PathVariable String lastName){
        return userService.getUserByLastName(lastName);
    }
}
