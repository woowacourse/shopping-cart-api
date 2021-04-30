package shoppingcart.exception;

public class NotInCustomerCartItemException extends RuntimeException {
    public NotInCustomerCartItemException(final String message) {
        super(message);
    }
}
