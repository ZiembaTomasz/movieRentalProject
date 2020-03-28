package pl.tomasz.project.rental.rental.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.tomasz.project.rental.rental.domain.UserRatingDto;
import pl.tomasz.project.rental.rental.mapper.UserRatingMapper;
import pl.tomasz.project.rental.rental.repository.UserRatingRepository;


import java.util.List;

@Component
@AllArgsConstructor
public class UserRatingService {
    private UserRatingRepository userRatingRepository;
    private UserRatingMapper userRatingMapper;

    public List<UserRatingDto>getAllUser(){
        return userRatingMapper.mapToUserRatingDtos(userRatingRepository.findAll());
    }

    public UserRatingDto getUserRating(Long userRatingId){
        return userRatingMapper.mapToUserRatingDto(userRatingRepository.getOne(userRatingId));
    }
}
