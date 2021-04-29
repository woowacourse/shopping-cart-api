package shoppingcart.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoppingcart.dao.CartDao;
import shoppingcart.dao.CustomerDao;
import shoppingcart.dao.OrderDao;
import shoppingcart.dao.OrdersDetailDao;
import shoppingcart.dao.ProductDao;
import shoppingcart.dto.OrderDetailRequestDto;
import shoppingcart.dto.OrderDetailResponseDto;
import shoppingcart.dto.OrderResponseDto;
import shoppingcart.dto.OrdersDetail;
import shoppingcart.dto.Product;

@Service
@Transactional
public class OrderService {

    private final OrderDao orderDao;
    private final OrdersDetailDao ordersDetailDao;
    private final CartDao cartDao;
    private final CustomerDao customerDao;
    private final ProductDao productDao;

    public OrderService(OrderDao orderDao, OrdersDetailDao ordersDetailDao,
            CartDao cartDao, CustomerDao customerDao, ProductDao productDao) {
        this.orderDao = orderDao;
        this.ordersDetailDao = ordersDetailDao;
        this.cartDao = cartDao;
        this.customerDao = customerDao;
        this.productDao = productDao;
    }

    // 주문 추가
    public Long addOrder(List<OrderDetailRequestDto> orderDetailRequests, String customerName) {
        Long customerId = customerDao.findIdByUserName(customerName);

        // 상품 id, quantity 매칭
        List<OrdersDetail> ordersDetails = new ArrayList<>();
        for (OrderDetailRequestDto ordersDetail : orderDetailRequests) {
            // todo orderDetail.getCartId() & customerName 검증
            Long productId = cartDao.findProductIdById(ordersDetail.getCartId());
            ordersDetails.add(new OrdersDetail(productId, ordersDetail.getQuantity()));
        }

        // 주문, 주문상세 생성
        Long orderId = orderDao.addOrders(customerId);
        for (OrdersDetail orderDetail : ordersDetails) {
            ordersDetailDao.addOrdersDetail(orderId, orderDetail);
        }

        // 장바구니 삭제
        for (OrderDetailRequestDto orderDetail : orderDetailRequests) {
            cartDao.deleteCartItem(orderDetail.getCartId());
        }

        return orderId;
    }

    // 주문 상세
    public OrderResponseDto findOrderById(String customerName, long orderId) {
        validateOrderIdByCustomerName(customerName, orderId);
        return findOrderResponseDtoByOrderId(orderId);
    }

    private void validateOrderIdByCustomerName(String customerName, long orderId) {
        Long customerId = customerDao.findIdByUserName(customerName);
        if (!orderDao.isValidOrderId(customerId, orderId)) {
            throw new RuntimeException("유저에게는 해당 order_id가 없습니다.");
        }
    }

    // 주문 목록
    public List<OrderResponseDto> findOrdersByCustomerName(String customerName) {
        Long customerId = customerDao.findIdByUserName(customerName);

        List<Long> orderIds = orderDao.findOrderIdsByCustomerId(customerId);

        List<OrderResponseDto> orderResponses = new ArrayList<>();
        for (Long orderId : orderIds) {
            orderResponses.add(findOrderResponseDtoByOrderId(orderId));
        }

        return orderResponses;
    }

    private OrderResponseDto findOrderResponseDtoByOrderId(long orderId) {
        List<OrdersDetail> ordersDetails = ordersDetailDao.findOrdersDetailsByOrderId(orderId);

        List<OrderDetailResponseDto> orderDetailResponses = new ArrayList<>();
        for (OrdersDetail ordersDetail : ordersDetails) {
            Product product = productDao.findProductById(ordersDetail.getProductId());
            orderDetailResponses.add(new OrderDetailResponseDto(product, ordersDetail.getQuantity()));
        }

        return new OrderResponseDto(orderId, orderDetailResponses);
    }
}
