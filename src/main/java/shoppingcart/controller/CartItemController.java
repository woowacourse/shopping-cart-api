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

    @GetMapping("/customers/{customer_name}/carts")
    public ResponseEntity<List<CartResponseDto>> getCartItems(@PathVariable String customer_name) {
        return ResponseEntity.ok().body(cartService.findCartsByCustomerName(customer_name));
    }

    @PostMapping("/customers/{customer_name}/carts")
    public ResponseEntity<Void> addCartItem(@RequestBody ProductIdRequestDto productIdRequestDto, @PathVariable String customer_name) {
        // productId가 있는지 체크 -> Exception 잡기
        long newId = cartService.addCart(productIdRequestDto.getProductId(), customer_name);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cart_id}")
                .buildAndExpand(newId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/customers/{customer_name}/carts/{cart_id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable String customer_name, @PathVariable long cart_id) {
        cartService.deleteCart(customer_name, cart_id);
        return ResponseEntity.status(204).build();
    }
}
