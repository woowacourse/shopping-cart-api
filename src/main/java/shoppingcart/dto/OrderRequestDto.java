package shoppingcart.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderRequestDto {
    @NotNull
    private final Long cartId;
    @Min(0)
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
