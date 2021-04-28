package shoppingcart.dto;

public class ProductDto {

    private final String name;
    private final Integer price;

    public ProductDto(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

}
