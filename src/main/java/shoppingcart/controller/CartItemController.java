package shoppingcart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shoppingcart.dto.CartResponseDto;
import shoppingcart.dto.ProductIdRequestDto;
import shoppingcart.service.CartService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers/{customerName}/carts")
public class CartItemController {
    private final CartService cartService;

    public CartItemController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartResponseDto>> getCartItems(@PathVariable String customerName) {
        return ResponseEntity.ok().body(cartService.findCartsByCustomerName(customerName));
    }

    @PostMapping
    public ResponseEntity<Void> addCartItem(@Valid @RequestBody ProductIdRequestDto productIdRequestDto,
                                            @PathVariable String customerName) {
        long newId = cartService.addCart(productIdRequestDto.getProductId(), customerName);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cartId}")
                .buildAndExpand(newId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable String customerName,
                                               @PathVariable Long cartId) {
        cartService.deleteCart(customerName, cartId);
        return ResponseEntity.status(204).build();
    }
}
