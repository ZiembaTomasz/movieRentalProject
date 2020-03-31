package pl.tomasz.project.rental.rental.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.tomasz.project.rental.rental.domain.User;
import pl.tomasz.project.rental.rental.domain.UserRating;
import pl.tomasz.project.rental.rental.domain.UserRatingDto;
import pl.tomasz.project.rental.rental.mapper.UserRatingMapper;
import pl.tomasz.project.rental.rental.repository.UserRatingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserRatingServiceTest {
    private UserRatingService userRatingService;
    private UserRating userRating;
    @Mock
    private UserRatingRepository userRatingRepository;

    private UserRatingMapper userRatingMapper = new UserRatingMapper();

    private User user;

    @Before
    public void createObjectUser() {
        user = new User(1L, "Tommy", "Lee", 123456789);
    }

    @Before
    public void createObjectUserRatingService() {
        userRatingService = new UserRatingService(userRatingRepository, userRatingMapper);
    }

    @Before
    public void createObjectUserRating() {
        userRating = new UserRating(1L, 8, user, 1L);

    }


    @Test
    public void getAllUserRatings() {
        //Given
        List<UserRating> userRatings = new ArrayList<>();
        userRatings.add(userRating);

        when(userRatingRepository.findAll()).thenReturn(userRatings);

        //When
        List<UserRatingDto> userRatingDtos = userRatingService.getAllUser();

        //Then
        assertEquals(1, userRatingDtos.size());

    }

    @Test
    public void shouldGetUserById() {
        //Given
        when(userRatingRepository.getOne(user.getId())).thenReturn(userRating);
        //When
        UserRatingDto userRatingDto = userRatingService.getUserRating(1L);
        //Then
        assertEquals(8, userRatingDto.getRate());
    }

    @Test
    public void shouldAddUserRating() {
        //Given
        UserRatingDto userRatingDto = userRatingMapper.mapToUserRatingDto(userRating);
        when(userRatingRepository.findById(1L)).thenReturn(Optional.of(userRating));
        //When
        userRatingService.addUserRating(userRatingDto);
        //Then
        verify(userRatingRepository, times(1)).save(userRating);
    }

    @Test
    public void shouldUpdateUserRating() {
        //Given
        UserRatingDto userRatingDto = userRatingMapper.mapToUserRatingDto(userRating);
        when(userRatingRepository.findById(1L)).thenReturn(Optional.of(userRating));
        //When
        UserRatingDto myUserRatingDto = userRatingService.updateUserRating(userRatingDto);
        //Then
        assertEquals(8, userRatingDto.getRate());

    }

    @Test
    public void shouldDeleteUserRating() {
        //Given
        Long userRatingId = userRating.getId();
        when(userRatingRepository.findById(1L)).thenReturn(Optional.of(userRating));
        //When
        userRatingService.deleteUserRating(userRatingId);
        //Then
        verify(userRatingRepository, times(1)).delete(userRating);
    }
}