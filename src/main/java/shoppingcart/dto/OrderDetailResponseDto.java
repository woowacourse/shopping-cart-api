package shoppingcart.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderDetailResponseDto {

    @NotNull(groups = TestGroup.request.class)
    private Long productId;
    private Long cartId;
    private int price;
    private String name;
    private String imageUrl;

    @Min(value = 0, groups = TestGroup.request.class)
    private int quantity;

    public OrderDetailResponseDto(final Long id, final int quantity) {
        this.productId = id;
        this.cartId = id;
        this.quantity = quantity;
    }

    public OrderDetailResponseDto(final Product product, final int quantity) {
        this(product.getId(), product.getPrice(), product.getName(), product.getImageUrl(), quantity);
    }

    public OrderDetailResponseDto(final Long productId, final int price, final String name,
                                  final String imageUrl, final int quantity) {
        this.productId = productId;
        this.price = price;
        this.name = name;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public Long getCartId() {
        return cartId;
    }
}
