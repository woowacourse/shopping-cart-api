package shoppingcart.dto;

public class ProductIdRequestDto {
    private Long productId;

    public ProductIdRequestDto() {
    }

    public ProductIdRequestDto(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
