package shoppingcart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shoppingcart.dto.CartResponseDto;
import shoppingcart.dto.ProductIdRequestDto;
import shoppingcart.service.CartService;

import java.net.URI;
import java.util.List;

@RestController
public class CartItemController {
    private final CartService cartService;

    public CartItemController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/customers/{customerName}/carts")
    public ResponseEntity<List<CartResponseDto>> getCartItems(@PathVariable String customerName) {
        return ResponseEntity.ok().body(cartService.findCartsByCustomerName(customerName));
    }

    @PostMapping("/customers/{customerName}/carts")
    public ResponseEntity<Void> addCartItem(@RequestBody ProductIdRequestDto productIdRequestDto, @PathVariable String customerName) {
        // productId가 있는지 체크 -> Exception 잡기
        long newId = cartService.addCart(productIdRequestDto.getProductId(), customerName);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cartId}")
                .buildAndExpand(newId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/customers/{customerName}/carts/{cartId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable String customerName, @PathVariable long cartId) {
        cartService.deleteCart(customerName, cartId);
        return ResponseEntity.status(204).build();
    }
}
