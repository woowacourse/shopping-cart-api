package shoppingcart.dto;

import java.util.List;

public class OrderResponseDto {

    private long orderId;
    private List<OrderDetailResponseDto> orderDetails;

    public OrderResponseDto(long orderId, List<OrderDetailResponseDto> orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<OrderDetailResponseDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailResponseDto> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
