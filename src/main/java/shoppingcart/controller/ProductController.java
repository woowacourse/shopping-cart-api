package shoppingcart.controller;

import static shoppingcart.controller.Constants.HOST;
import static shoppingcart.controller.Constants.PORT;
import static shoppingcart.controller.Constants.PRODUCTS_PATH;
import static shoppingcart.controller.Constants.SCHEME;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import shoppingcart.dto.Product;
import shoppingcart.dto.ProductRequestDto;
import shoppingcart.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> products() {
        return ResponseEntity.ok(productService.findProducts());
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody ProductRequestDto productRequestDto) {
        Long productId = productService.addProduct(productRequestDto);
        URI uri = UriComponentsBuilder.newInstance()
                .scheme(SCHEME).host(HOST).port(PORT).path(PRODUCTS_PATH).path("/" + productId)
                .build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Product> product(@PathVariable("product_id") Long productId){
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<Void> delete(@PathVariable("product_id") Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }
}
