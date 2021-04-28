package shoppingcart.dao;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import shoppingcart.dto.ProductRequestDto;
import shoppingcart.dto.ProductResponseDto;

@JdbcTest
@Sql("classpath:InitCustomerTable.sql")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class ProductDaoTest {

    private final ProductDao productDao;

    public ProductDaoTest(JdbcTemplate jdbcTemplate) {
        this.productDao = new ProductDao(jdbcTemplate);
    }

    @DisplayName("Product를 저장하면, id를 반환한다.")
    @Test
    void save() {
        // given
        String name = "초콜렛";
        int price = 1_000;
        String imageUrl = "www.test.com";

        // when
        Long productId = productDao.save(new ProductRequestDto(name, price, imageUrl));

        // then
        assertThat(productId).isEqualTo(1L);
    }

    @DisplayName("productID를 상품을 찾으면, product를 반환한다.")
    @Test
    void findProductById() {
        // given
        String name = "초콜렛";
        int price = 1_000;
        String imageUrl = "www.test.com";
        Long productId = productDao.save(new ProductRequestDto(name, price, imageUrl));
        ProductResponseDto expectedProductDto = new ProductResponseDto(productId, name, price, imageUrl);

        // when
        final ProductResponseDto product = productDao.findProductById(productId);

        // then
        assertThat(product).usingRecursiveComparison().isEqualTo(expectedProductDto);
    }

    @DisplayName("상품 목록 조회")
    @Test
    void getProducts(){

        // given
        int size = 0;

        // when
        final List<ProductResponseDto> products = productDao.findProducts();

        // then
        assertThat(products).size().isEqualTo(size);
    }

    @DisplayName("싱품 삭제")
    @Test
    void deleteProduct(){
        // given
        String name = "초콜렛";
        int price = 1_000;
        String imageUrl = "www.test.com";

        Long productId = productDao.save(new ProductRequestDto(name, price, imageUrl));
        int beforeSize = productDao.findProducts().size();

        // when
        productDao.delete(productId);

        // then
        int afterSize = productDao.findProducts().size();
        assertThat(beforeSize-1).isEqualTo(afterSize);
    }
}
