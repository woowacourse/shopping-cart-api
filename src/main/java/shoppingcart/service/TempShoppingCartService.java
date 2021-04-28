package shoppingcart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import shoppingcart.dao.CartDao;
import shoppingcart.dao.CustomerDao;
import shoppingcart.dao.OrderDao;
import shoppingcart.dao.OrdersDetailDao;
import shoppingcart.dao.ProductDao;
import shoppingcart.dto.CartResponseDto;
import shoppingcart.dto.ProductRequestDto;
import shoppingcart.dto.ProductResponseDto;

@Service
public class TempShoppingCartService {
    private final OrderDao orderDao;
    private final OrdersDetailDao ordersDetailDao;
    private final CartDao cartDao;
    private final ProductDao productDao;
    private final CustomerDao customerDao;

    public TempShoppingCartService(OrderDao orderDao, OrdersDetailDao ordersDetailDao, CartDao cartDao, ProductDao productDao, CustomerDao customerDao) {
        this.orderDao = orderDao;
        this.ordersDetailDao = ordersDetailDao;
        this.cartDao = cartDao;
        this.productDao = productDao;
        this.customerDao = customerDao;
    }

    // 전체 상품 조회
    public List<ProductResponseDto> findProducts() {
        return productDao.findProducts();
    }

    // 상품 추가
    public Long addProduct(ProductRequestDto productRequestDto) {
        final Long id = productDao.save(productRequestDto);
        return id;
    }

    // 단일 조회
    public ProductResponseDto findProductById(long productId) {
        return productDao.findProductById(productId);
    }

    // 단일 삭제
    public void deleteProductById(long productId) {
        productDao.delete(productId);
    }

    // 장바구니 조회
    public List<CartResponseDto> findCartsByCustomerName(String name) {
        // [customerDao] input: name - output: customerId
        final Long customerId = customerDao.findIdByUserName(name);
        // [cartDao] input: customerId - output: productId, cartId
        final List<Long> productIds = cartDao.findProductIdsByCustomerId(customerId);
        final List<Long> cartIds = cartDao.findIdsByCustomerId(customerId);
        // [productDao] input: productId - output: productResponseDto
        List<ProductResponseDto> productResponseDtos = productDao.findProductsByIds(productIds);

        List<CartResponseDto> cartResponseDtos = new ArrayList<>();

        for (int i = 0; i < productIds.size(); i++) {
            final Long cartId = cartIds.get(i);

            final ProductResponseDto productResponseDto = productResponseDtos.get(i);
            final CartResponseDto cartResponseDto =
                    new CartResponseDto(cartId, productResponseDto.getId(),
                            productResponseDto.getName(), productResponseDto.getPrice(),
                            productResponseDto.getImageUrl());
            cartResponseDtos.add(cartResponseDto);
        }
        // return Dto - cartId, name, price, image_url    List<CartResponseDto>
        return cartResponseDtos;
    }

    // 코기
    public List<CartResponseDto> findCartsByCustomerName_temp(final String name) {
        final Long customerId = customerDao.findIdByUserName(name);
        final List<Long> cartIds = cartDao.findIdsByCustomerId(customerId);

        final List<CartResponseDto> cartResponseDtos = new ArrayList<>();
        for (final Long cartId : cartIds) {
            final Long productId = cartDao.findProductIdById(cartId);
            final ProductResponseDto product = productDao.findProductById(productId);
            cartResponseDtos.add(new CartResponseDto(cartId, product));
        }
        return cartResponseDtos;
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
