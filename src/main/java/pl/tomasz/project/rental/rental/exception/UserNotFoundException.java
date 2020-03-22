package pl.tomasz.project.rental.rental.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("User doesnt exist");
    }
}
