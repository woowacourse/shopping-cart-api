package shoppingcart.dto;

import java.util.List;

public class OrderDto {

    private final Long orderId;
    private final List<OrderDetailDto> orderDetails;

    public OrderDto(final Long orderId, final List<OrderDetailDto> orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    public Long getOrderId() {
        return orderId;
    }

    public List<OrderDetailDto> getOrderDetails() {
        return orderDetails;
    }
}
