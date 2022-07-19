package pl.macieksob.rentCar.exception;

public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(String message){
        super(message);
    }
}
