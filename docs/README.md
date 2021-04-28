``` java
package shoppingcart.service;

import org.springframework.stereotype.Service;
import shoppingcart.dao.CartDao;
import shoppingcart.dao.OrderDao;
import shoppingcart.dao.OrdersDetailDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {
    private final OrderDao orderDao;
    private final OrdersDetailDao ordersDetailDao;
    private final CartDao cartDao;
    private final ProductDao productDao;
    private final CustomerDao customerDao;

    public ShoppingCartService(OrderDao orderDao, OrdersDetailDao ordersDetailDao, CartDao cartDao, ProductDao productDao, CustomerDao customerDao) {
        this.orderDao = orderDao;
        this.ordersDetailDao = ordersDetailDao;
        this.cartDao = cartDao;
        this.productDao = productDao;
        this.customerDao = customerDao;
    }

    // 전체 상품 조회
    public List<Void> findProducts() {
        // productDao에서 findAll()
        return new ArrayList<>();
    }

    // 상품 추가
    public Void addProduct() {
        // productDao - price, name, image_url
        return null;
    }

    // 단일 조회
    public Void findProductById(long productId) {
        return null;
    }

    // 단일 삭제
    public void deleteProductById(long productId) {
        return;
    }

    // 장바구니 조회
    public List<Void> findCartsByCustomerName(String name) {
        // [customerDao] input: name - output: customerId
        // [cartDao] input: customerId - output: productId, cartId
        // [productDao] input: productId - output: name, price, image_url
        // return Dto - cartId, name, price, image_url
        return new ArrayList<>();
    }

    // 장바구니 상품 추가
    public Void addCart(long productId, String name) {
        // [customerDao] input: name - output: customerId
        // [cartDao] input: customerId, productId
        // return URI - cartId
        return null;
    }

    // 장바구니 단일 삭제
    public void deleteCart(long cartId) {
        // [cartDao] input: cartId
    }

    // 주문 추가
    public Void addOrder(List<Void> orderDtos, String name) {
        // 유저 가져오기
        // orderRequestDtos(cartId, quantity)
        // [customerDao] input: name - output: customerId

        // 장바구니 아이디 가져오기
        // [cartDao] input: cartId - output: List<productId, cartId>
        // 인자로 받은 orderRequestDtos의 cartId, cartDao에서 받은 dto의 cartId를 1대1 매칭해서 DTO(productId, quantity)생성

        // 주문, 주문상세 생성
        // [orderDao] input: customerId - output: orderId
        // 반복 insert(List<Dto>)
        // [orderDetailDao] input: orderId, productId, quantity - output: List<productId>

        // 장바구니 삭제
        // [cartDao] input: List<cartId>

        // return URI - /api/customers/{customer_id}/orders/{order_id}
        return null;
    }

    // 주문 목록
    public List<Void> findOrders(String name) {
        // 유저 가져오기
        // orderRequestDtos - cartId, quantity
        // [customerDao] input: name - output: customerId

        // [orderDao] input: customerId - output: List<orderId>
        // [orderDetailDao] input: List<orderId> - output: List<OrdersDetailDTO(orderDetailId, orderId, productId, quantity)>

        // RowMapper 에서 productId 기준으로 매칭해서 ProductDetailDTO(product_id, name, price, img, quantity)생성
        // [productDao] input: List<OrdersDetailDTO> - output: List<ProductDetailDTO>
        // List<orderId> -> List<OrdersDto(orderId, List<ProductDetailDTO>)>
        return null;
    }

    // 주문 상세
    public Void findOrderById(String name) {
        // 유저 가져오기
        // orderRequestDtos - cartId, quantity
        // [customerDao] input: name - output: customerId

        //
        return null;
    }
}

```