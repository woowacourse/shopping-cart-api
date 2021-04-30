package shoppingcart.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDto {
    private Long id;

    @NotBlank(groups = TestGroup.request.class)
    private String name;

    @Min(value = 0, message = "금액은 음수일 수 없습니다.", groups = TestGroup.request.class)
    private Integer price;

    @NotBlank(groups = TestGroup.request.class)
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

    public Long getId() {
        return id;
    }
}
