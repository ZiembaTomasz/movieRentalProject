package pl.tomasz.project.rental.rental.controller;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(){
        super("Movie doesnt exist");
    }
}
