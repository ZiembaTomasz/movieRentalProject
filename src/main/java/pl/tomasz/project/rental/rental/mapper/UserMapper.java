package pl.tomasz.project.rental.rental.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.tomasz.project.rental.rental.domain.User;
import pl.tomasz.project.rental.rental.domain.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserMapper {
    public User mapToUser(UserDto userDto){
        return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getAccountNumber());
    }
    public UserDto mapToUserDto(User user){
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getAccountNumber());
    }
    public List<UserDto> mapToUserDtoList(List<User>userList){
        return userList.stream()
                .map(t-> mapToUserDto(t))
                .collect(Collectors.toList());
    }
}
