package pl.tomasz.project.rental.rental.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.tomasz.project.rental.rental.domain.User;
import pl.tomasz.project.rental.rental.domain.UserDto;
import pl.tomasz.project.rental.rental.mapper.UserMapper;
import pl.tomasz.project.rental.rental.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    UserService userService;
    UserMapper userMapper = new UserMapper();
    User user;

    @Before
    public void createUserServiceObject(){
        userService = new UserService(userMapper, userRepository);
    }
    @Before
    public void createUser(){
        user = new User(1L, "George", "Watkinson", 12345);
    }

    @Test
    public void shouldGetAllUsers(){
        //Given
        List<User>users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);
        //When
        List<UserDto>myUsers = userService.getAllUsers();
        //Then
        assertEquals(1, myUsers.size());
    }
    @Test
    public void shouldAddUser(){
        //When
        userService.addUser(userMapper.mapToUserDto(user));
        //Then
        verify(userRepository, times(1)).save(user);
    }
    @Test
    public void shouldGetUserById(){
        //Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        //When
        UserDto userDto = userService.getUserById(1L);
        //Then
        assertEquals(1L, userDto.getId());
    }
    @Test
    public void shouldDeleteUser(){
        //Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        //When
        userService.deleteUser(user.getId());
        //Then
        verify(userRepository, times(1)).deleteById(1L);
    }
    @Test
    public void shouldGetUserByAccountNumber(){
        //Given
        when(userRepository.findUserByAccountNumber(12345)).thenReturn(user);
        //When
        UserDto userDto = userService.getUserByAccountNumber(12345);
        //Then
        assertEquals(12345, userDto.getAccountNumber());
    }
}