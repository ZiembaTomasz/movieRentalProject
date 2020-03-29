package pl.tomasz.project.rental.rental.service;

import lombok.AllArgsConstructor;
import org.hibernate.validator.internal.util.Contracts;
import org.springframework.stereotype.Component;
import pl.tomasz.project.rental.rental.domain.UserRating;
import pl.tomasz.project.rental.rental.domain.UserRatingDto;
import pl.tomasz.project.rental.rental.exception.UserRatingNotFoundException;
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
    public UserRatingDto updateUserRating(UserRatingDto userRatingDto) {
        Contracts.assertNotNull(userRatingDto.getId(), "Cannot update with no iD");
        UserRating userRating = userRatingMapper.mapToUserRating(userRatingDto);
        Contracts.assertNotNull(userRatingRepository.findById(userRating.getId()).orElseThrow(UserRatingNotFoundException::new));
        userRatingRepository.save(userRating);
        return userRatingMapper.mapToUserRatingDto(userRating);
    }
    public void deleteUserRating(Long userRatingId){
        UserRating userRating = userRatingRepository.findById(userRatingId).orElseThrow(UserRatingNotFoundException::new);
        userRatingRepository.delete(userRating);
    }
    public void addUserRating(UserRatingDto userRatingDto){
        Contracts.assertNotNull(userRatingDto.getId(), "Cannot save with no ID");
        UserRating userRating = userRatingMapper.mapToUserRating(userRatingDto);
        userRatingRepository.save(userRating);
    }
}
