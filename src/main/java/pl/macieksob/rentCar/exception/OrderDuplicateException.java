package pl.macieksob.rentCar.exception;

public class OrderDuplicateException extends RuntimeException{

    public OrderDuplicateException(String message){
        super(message);
    }
}
