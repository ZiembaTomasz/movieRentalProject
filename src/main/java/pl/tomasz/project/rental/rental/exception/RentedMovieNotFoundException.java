package pl.tomasz.project.rental.rental.exception;

public class RentedMovieNotFoundException extends RuntimeException {
    public RentedMovieNotFoundException(){
        super("Rented Movie doesnt exist");
    }
}
