//package shoppingcart.dto;
//
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
//
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//public class OrderDetailRequestDto {
//    @NotNull
//    private final Long cartId;
//    @Min(0)
//    private final int quantity;
//
//    public OrderDetailRequestDto(final Long cartId, final int quantity) {
//        this.cartId = cartId;
//        this.quantity = quantity;
//    }
//
//    public Long getCartId() {
//        return cartId;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//}
