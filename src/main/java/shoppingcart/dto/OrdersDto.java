package shoppingcart.dto;

import java.util.List;

public class OrdersDto {

    private final Long orderId;
    private final List<OrderDetailDto> orderDetails;

    public OrdersDto(final Long orderId, final List<OrderDetailDto> orderDetails) {
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
