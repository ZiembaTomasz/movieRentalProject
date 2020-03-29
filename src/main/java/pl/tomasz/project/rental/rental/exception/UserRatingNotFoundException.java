package pl.tomasz.project.rental.rental.exception;

public class UserRatingNotFoundException extends RuntimeException{
    public UserRatingNotFoundException(){
        super("User Rating doesnt exist");
    }

}
