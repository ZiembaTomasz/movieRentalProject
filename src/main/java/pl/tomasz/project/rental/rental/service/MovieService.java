package pl.tomasz.project.rental.rental.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import pl.tomasz.project.rental.rental.domain.MovieDto;
import pl.tomasz.project.rental.rental.interfaces.MovieType;
import pl.tomasz.project.rental.rental.mapper.MovieMapper;
import pl.tomasz.project.rental.rental.repository.MovieRepository;

import java.util.List;

@Service
@Getter
@AllArgsConstructor
public class MovieService {
    MovieType movieType;
    MovieMapper movieMapper;
    MovieRepository movieRepository;

    public int priceOfMovie(MovieType movieType, int days){
        if(movieType == MovieType.NEW_MOVIE && days <= 3){
            return 20;
        }
        if (movieType == MovieType.NEW_MOVIE && days > 3){
            return 20 * (days - 3);
        }
        if (movieType == MovieType.BASIC_MOVIE && days <= 3){
            return 15;
        }
        if (movieType == MovieType.BASIC_MOVIE && days > 3) {
            return 15 * (days - 3);
        }

        if (movieType == MovieType.OLD_MOVIE && days <= 3){
            return 10;
        }
        if (movieType == MovieType.OLD_MOVIE && days > 3){
            return 10 * (days - 3);
        }
        return 0;
    }
    public List<MovieDto>getAllMovies(){
       return movieMapper.mapToMovieDtoList(movieRepository.findAll());
    }
}
