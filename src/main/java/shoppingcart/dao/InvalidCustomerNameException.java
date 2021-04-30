package shoppingcart.dao;

public class InvalidCustomerNameException extends RuntimeException {
    public InvalidCustomerNameException(final String message) {
        super(message);
    }
}
