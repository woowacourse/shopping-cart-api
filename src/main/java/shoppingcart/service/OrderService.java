package shoppingcart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoppingcart.dao.*;
import shoppingcart.dto.OrderDetailDto;
import shoppingcart.dto.OrdersDto;
import shoppingcart.dto.OrderRequestDto;
import shoppingcart.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final OrderDao orderDao;
    private final OrdersDetailDao ordersDetailDao;
    private final CartItemDao cartItemDao;
    private final CustomerDao customerDao;
    private final ProductDao productDao;

    public OrderService(final OrderDao orderDao, final OrdersDetailDao ordersDetailDao,
                        final CartItemDao cartItemDao, final CustomerDao customerDao, final ProductDao productDao) {
        this.orderDao = orderDao;
        this.ordersDetailDao = ordersDetailDao;
        this.cartItemDao = cartItemDao;
        this.customerDao = customerDao;
        this.productDao = productDao;
    }

    public Long addOrder(final List<OrderRequestDto> orderDetailRequests, final String customerName) {
        final Long customerId = customerDao.findIdByUserName(customerName);
        final Long orderId = orderDao.addOrders(customerId);

        for (final OrderDetailDto orderDetail : getOrdersDetails(orderDetailRequests)) {
            ordersDetailDao.addOrdersDetail(orderId, orderDetail);
        }

        for (final OrderRequestDto orderDetail : orderDetailRequests) {
            cartItemDao.deleteCartItem(orderDetail.getCartId());
        }
        return orderId;
    }

    private List<OrderDetailDto> getOrdersDetails(final List<OrderRequestDto> orderRequestDtos) {
        final List<OrderDetailDto> ordersDetails = new ArrayList<>();
        for (final OrderRequestDto ordersDetail : orderRequestDtos) {
            final Long productId = cartItemDao.findProductIdById(ordersDetail.getCartId());
            ordersDetails.add(new OrderDetailDto(productId, ordersDetail.getQuantity()));
        }
        return ordersDetails;
    }

    public OrdersDto findOrderById(final String customerName, final Long orderId) {
        validateOrderIdByCustomerName(customerName, orderId);
        return findOrderResponseDtoByOrderId(orderId);
    }

    private void validateOrderIdByCustomerName(final String customerName, final Long orderId) {
        final Long customerId = customerDao.findIdByUserName(customerName);

        if (!orderDao.isValidOrderId(customerId, orderId)) {
            throw new RuntimeException("유저에게는 해당 order_id가 없습니다.");
        }
    }

    public List<OrdersDto> findOrdersByCustomerName(final String customerName) {
        final Long customerId = customerDao.findIdByUserName(customerName);
        final List<Long> orderIds = orderDao.findOrderIdsByCustomerId(customerId);

        return orderIds.stream()
                .map(orderId -> findOrderResponseDtoByOrderId(orderId))
                .collect(Collectors.toList());
    }

    private OrdersDto findOrderResponseDtoByOrderId(final Long orderId) {
        final List<OrderDetailDto> ordersDetails = ordersDetailDao.findOrdersDetailsByOrderId(orderId);

        final List<OrderDetailDto> orderDetailResponses = new ArrayList<>();
        for (final OrderDetailDto ordersDetail : ordersDetails) {
            final ProductDto product = productDao.findProductById(ordersDetail.getProductId());
            orderDetailResponses.add(new OrderDetailDto(product, ordersDetail.getQuantity()));
        }
        return new OrdersDto(orderId, orderDetailResponses);
    }
}
