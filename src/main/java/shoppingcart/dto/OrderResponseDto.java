package shoppingcart.dto;

import java.util.List;

public class OrderResponseDto {

    private final Long orderId;
    private final List<OrderDetailResponseDto> orderDetails;

    public OrderResponseDto(final Long orderId, final List<OrderDetailResponseDto> orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    public Long getOrderId() {
        return orderId;
    }

    public List<OrderDetailResponseDto> getOrderDetails() {
        return orderDetails;
    }
}
