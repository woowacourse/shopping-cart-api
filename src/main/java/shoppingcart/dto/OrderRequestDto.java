package shoppingcart.dto;

public class OrderRequestDto {

    private final Long cartId;
    private final int quantity;

    public OrderRequestDto(final Long cartId, final int quantity) {
        this.cartId = cartId;
        this.quantity = quantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public int getQuantity() {
        return quantity;
    }
}
