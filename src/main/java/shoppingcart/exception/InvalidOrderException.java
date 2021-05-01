package shoppingcart.exception;

public class InvalidOrderException extends RuntimeException{
    public InvalidOrderException() {
        this("유효하지 않은 주문입니다.");
    }

    public InvalidOrderException(final String msg) {
        super(msg);
    }
}
