package shoppingcart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoppingcart.dao.CartItemDao;
import shoppingcart.dao.CustomerDao;
import shoppingcart.dao.ProductDao;
import shoppingcart.dto.CartResponseDto;
import shoppingcart.dto.Product;
import shoppingcart.exception.InvalidProductException;
import shoppingcart.exception.NotInCustomerCartItemException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartService {

    private final CartItemDao cartItemDao;
    private final CustomerDao customerDao;
    private final ProductDao productDao;

    public CartService(CartItemDao cartItemDao, CustomerDao customerDao, ProductDao productDao) {
        this.cartItemDao = cartItemDao;
        this.customerDao = customerDao;
        this.productDao = productDao;
    }

    public List<CartResponseDto> findCartsByCustomerName(final String customerName) {
        final Long customerId = customerDao.findIdByUserName(customerName);
        final List<Long> cartIds = cartItemDao.findIdsByCustomerId(customerId);

        final List<CartResponseDto> cartResponseDtos = new ArrayList<>();
        for (final Long cartId : cartIds) {
            final Long productId = cartItemDao.findProductIdById(cartId);
            final Product product = productDao.findProductById(productId);
            cartResponseDtos.add(new CartResponseDto(cartId, product));
        }
        return cartResponseDtos;
    }

    public long addCart(long productId, String customerName) {
        Long customerId = customerDao.findIdByUserName(customerName);
        try {
            return cartItemDao.addCartItem(customerId, productId);
        } catch (Exception e) {
            throw new InvalidProductException("올바르지 않은 사용자 이름이거나 상품 아이디 입니다.");
        }
    }

    public void deleteCart(String customerName, long cartId) {
        List<CartResponseDto> cartResponseDtos = findCartsByCustomerName(customerName);
        validateCustomerCart(cartId, cartResponseDtos);
        cartItemDao.deleteCartItem(cartId);
    }

    private void validateCustomerCart(long cartId, List<CartResponseDto> cartResponseDtos) {
        cartResponseDtos.stream()
                .mapToLong(CartResponseDto::getCartId)
                .filter(cartDtoId -> cartDtoId == cartId)
                .findAny()
                .orElseThrow(() -> new NotInCustomerCartItemException("장바구니 아이템이 없습니다."));
    }
}
