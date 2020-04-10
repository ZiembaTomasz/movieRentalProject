package pl.tomasz.project.rental.rental.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.internal.util.Contracts;
import org.springframework.stereotype.Service;
import pl.tomasz.project.rental.rental.domain.User;
import pl.tomasz.project.rental.rental.domain.UserDto;
import pl.tomasz.project.rental.rental.exception.UserNotFoundException;
import pl.tomasz.project.rental.rental.mapper.UserMapper;
import pl.tomasz.project.rental.rental.repository.UserRepository;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class UserService {
     UserMapper userMapper;
     UserRepository userRepository;

    public List<UserDto>getAllUsers(){
       return userMapper.mapToUserDtoList(userRepository.findAll());
    }
    public UserDto getUserById(Long userId)throws UserNotFoundException{
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userMapper.mapToUserDto(user);
    }
    public void addUser(UserDto userDto){
        Contracts.assertNotNull(userDto, "Cannot Save empty User");
        User user = userMapper.mapToUser(userDto);
        userRepository.save(user);
    }
    public UserDto updateUser(UserDto userDto){
        Contracts.assertNotNull(userDto, "Cannot Save empty User");
        User user = userMapper.mapToUser(userDto);
        Contracts.assertNotNull(userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new));
        userRepository.save(user);
        return userMapper.mapToUserDto(user);
    }
    public void deleteUser(Long userId){
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(userId);
    }
    public UserDto getUserByAccountNumber(int accountNumber){
        User user = userRepository.findUserByAccountNumber(accountNumber);
        return userMapper.mapToUserDto(user);
    }
    public UserDto getUserByLastName(String name){
        User user = userRepository.findUserByLastName(name);
        return userMapper.mapToUserDto(user);
    }

}
