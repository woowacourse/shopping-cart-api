package shoppingcart.exception;

public class InvalidCartItemException extends RuntimeException{
    public InvalidCartItemException() {
        this("유효하지 않은 장바구니입니다.");
    }

    public InvalidCartItemException(final String msg) {
        super(msg);
    }
}
