package shoppingcart.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoppingcart.dao.CartDao;
import shoppingcart.dao.CustomerDao;
import shoppingcart.dao.ProductDao;
import shoppingcart.dto.CartResponseDto;
import shoppingcart.dto.Product;

@Service
@Transactional
public class CartService {

    private final CartDao cartDao;
    private final CustomerDao customerDao;
    private final ProductDao productDao;

    public CartService(CartDao cartDao, CustomerDao customerDao, ProductDao productDao) {
        this.cartDao = cartDao;
        this.customerDao = customerDao;
        this.productDao = productDao;
    }

    // 장바구니 목록 조회
    public List<CartResponseDto> findCartsByCustomerName(final String customerName) {
        final Long customerId = customerDao.findIdByUserName(customerName);
        final List<Long> cartIds = cartDao.findIdsByCustomerId(customerId);

        final List<CartResponseDto> cartResponseDtos = new ArrayList<>();
        for (final Long cartId : cartIds) {
            final Long productId = cartDao.findProductIdById(cartId);
            final Product product = productDao.findProductById(productId);
            cartResponseDtos.add(new CartResponseDto(cartId, product));
        }
        return cartResponseDtos;
    }

    // 장바구니 상품 추가
    public long addCart(long productId, String customerName) {
        Long customerId = customerDao.findIdByUserName(customerName);
        return cartDao.addCartItem(customerId, productId);
    }

    // 장바구니 단일 삭제
    public void deleteCart(String customerName, long cartId) {
        // todo customer 유효성 검사
        cartDao.deleteCartItem(cartId);
    }
}
