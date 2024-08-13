package uz.dual.paymejava.exceptions;

public class WrongAmountException extends RuntimeException{

    public WrongAmountException(String message) {
        super(message);
    }
}
