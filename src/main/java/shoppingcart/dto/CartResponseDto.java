package shoppingcart.dto;

public class CartResponseDto {

    private Long cartId;
    private Long productId;
    private String name;
    private Integer price;
    private String imageUrl;

    public CartResponseDto(Long cartId, ProductResponseDto productResponseDto) {
        this(cartId, productResponseDto.getId(),
                productResponseDto.getName(), productResponseDto.getPrice(),
                productResponseDto.getImageUrl());
    }

    public CartResponseDto(Long cartId, Long productId, String name, Integer price,
                           String imageUrl) {
        this.cartId = cartId;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

}
