package shoppingcart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shoppingcart.dto.CartDto;
import shoppingcart.dto.ProductDto;
import shoppingcart.dto.Request;
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
    public ResponseEntity addCartItem(@Validated(Request.id.class) @RequestBody final ProductDto product,
                                      @PathVariable final String customerName) {
        final Long cartId = cartService.addCart(product.getProductId(), customerName);
        final URI responseLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cartId}")
                .buildAndExpand(cartId)
                .toUri();
        return ResponseEntity.created(responseLocation).build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity deleteCartItem(@PathVariable final String customerName,
                                         @PathVariable final Long cartId) {
        cartService.deleteCart(customerName, cartId);
        return ResponseEntity.noContent().build();
    }
}
