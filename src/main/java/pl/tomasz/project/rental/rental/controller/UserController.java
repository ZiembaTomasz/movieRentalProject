package pl.tomasz.project.rental.rental.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.tomasz.project.rental.rental.domain.UserDto;
import pl.tomasz.project.rental.rental.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "addUser")
    public void addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
    }
    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto>getAllUsers(){
        return userService.getAllUsers();
    }
    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long userId){
        return userService.getUser(userId);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteMovie(@RequestParam Long movieId){
        userService.deleteUser(movieId);
    }
}
