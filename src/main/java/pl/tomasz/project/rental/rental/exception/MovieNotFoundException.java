package pl.tomasz.project.rental.rental.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(){
        super("Movie doesnt exist");
    }
}
