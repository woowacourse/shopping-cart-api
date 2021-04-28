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

    // 장바구니 삭제
    public void deleteCart(long cartId) {

    }
}
