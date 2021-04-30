package shoppingcart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shoppingcart.dto.CartDto;
import shoppingcart.dto.ProductDto;
import shoppingcart.dto.TestGroup;
import shoppingcart.service.CartService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers/{customerName}/carts")
public class CartItemController {
    private final CartService cartService;

    public CartItemController(final CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getCartItems(@PathVariable final String customerName) {
        return ResponseEntity.ok().body(cartService.findCartsByCustomerName(customerName));
    }

    @PostMapping
    public ResponseEntity<Void> addCartItem(@Validated(TestGroup.response.class) @RequestBody final ProductDto productDto,
                                            @PathVariable final String customerName) {
        final Long newId = cartService.addCart(productDto.getId(), customerName);
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cartId}")
                .buildAndExpand(newId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable final String customerName,
                                               @PathVariable final Long cartId) {
        cartService.deleteCart(customerName, cartId);
        return ResponseEntity.status(204).build();
    }
}
