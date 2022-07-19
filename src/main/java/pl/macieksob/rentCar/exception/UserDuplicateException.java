package pl.macieksob.rentCar.exception;

public class UserDuplicateException extends RuntimeException{

    public UserDuplicateException(String message){
        super(message);
    }
}
