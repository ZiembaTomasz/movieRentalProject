package pl.tomasz.project.rental.rental.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.internal.util.Contracts;
import org.springframework.stereotype.Service;
import pl.tomasz.project.rental.rental.domain.User;
import pl.tomasz.project.rental.rental.domain.UserDto;
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
    public UserDto getUser(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        return userMapper.mapToUserDto(user);
    }
    public void addUser(UserDto userDto){
        Contracts.assertNotNull(userDto, "Cannot Save empty User");
        User user = userMapper.mapToUser(userDto);
        userRepository.save(user);
    }
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        userRepository.delete(user);
    }
}
