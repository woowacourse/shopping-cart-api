package shoppingcart.dto;

public class CartDto {

    private final Long cartId;
    private final Long productId;
    private final String name;
    private final int price;
    private final String imageUrl;

    public CartDto(final Long cartId, final Product product) {
        this(cartId, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public CartDto(final Long cartId, final Long productId, final String name, final int price, final String imageUrl) {
        this.cartId = cartId;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
