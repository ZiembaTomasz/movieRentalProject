package pl.tomasz.project.rental.rental.mapper;

import org.springframework.stereotype.Component;
import pl.tomasz.project.rental.rental.domain.UserRating;
import pl.tomasz.project.rental.rental.domain.UserRatingDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRatingMapper {
    public UserRating mapToUserRating(UserRatingDto userRatingDto){
        return new UserRating(userRatingDto.getId(),userRatingDto.getRate(), userRatingDto.getUser(),
                userRatingDto.getMovieId());
    }
    public UserRatingDto mapToUserRatingDto(UserRating userRating){
        return new UserRatingDto(userRating.getId(), userRating.getRate(), userRating.getUser(),
                userRating.getMovieId());
    }
    public List<UserRatingDto>mapToUserRatingDtos(List<UserRating>userRatings){
        return userRatings.stream()
                .map(t-> mapToUserRatingDto(t))
                .collect(Collectors.toList());

    }

}
