package shoppingcart.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDto {
    @NotNull(groups = Request.id.class)
    private Long productId;

    @NotBlank(groups = Request.allProperties.class)
    private String name;

    @Min(value = 0, message = "금액은 음수일 수 없습니다.", groups = Request.allProperties.class)
    private Integer price;

    @NotBlank(groups = Request.allProperties.class)
    private String imageUrl;

    public ProductDto() {
    }

    public ProductDto(final String name, final int price, final String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
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

    public Long getProductId() {
        return productId;
    }
}
