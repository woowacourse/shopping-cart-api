package shoppingcart.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderDetailDto {

    @NotNull(groups = Request.allProperties.class)
    private Long productId;

    @Min(value = 0, groups = Request.allProperties.class)
    private int quantity;

    private int price;
    private String name;
    private String imageUrl;

    public OrderDetailDto() {
    }

    public OrderDetailDto(final Long productId, final int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderDetailDto(final ProductDto product, final int quantity) {
        this(product.getProductId(), product.getPrice(), product.getName(), product.getImageUrl(), quantity);
    }

    public OrderDetailDto(final Long productId, final int price, final String name,
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
}
