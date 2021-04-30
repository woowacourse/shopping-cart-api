package shoppingcart.dao;

public class InvalidCustomerNameException extends RuntimeException {
    public InvalidCustomerNameException(String message) {
        super(message);
    }
}
