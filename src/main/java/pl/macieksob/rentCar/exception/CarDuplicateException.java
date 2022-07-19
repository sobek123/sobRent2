package pl.macieksob.rentCar.exception;

public class CarDuplicateException extends RuntimeException{

    public CarDuplicateException(String message){
        super(message);
    }
}
