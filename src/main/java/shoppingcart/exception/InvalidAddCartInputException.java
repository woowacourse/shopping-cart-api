package shoppingcart.exception;

public class InvalidAddCartInputException extends RuntimeException {
    public InvalidAddCartInputException(String message) {
        super(message);
    }
}
