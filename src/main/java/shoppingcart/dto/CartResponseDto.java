package shoppingcart.dto;

public class CartResponseDto {

    private Long cartId;
    private Long productId;
    private String name;
    private Integer price;
    private String imageUrl;

    public CartResponseDto(Long cartId, Product product) {
        this(cartId, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public CartResponseDto(Long cartId, Long productId, String name, Integer price,
            String imageUrl) {
        this.cartId = cartId;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
