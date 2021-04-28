package shoppingcart.dao;

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

        // when
        Long productId = productDao.save(new ProductRequestDto(name, price));

        // then
        assertThat(productId).isEqualTo(1L);
    }

    @DisplayName("productID를 상품을 찾으면, product를 반환한다.")
    @Test
    void findProductById() {
        // given
        String name = "초콜렛";
        int price = 1_000;
        Long productId = productDao.save(new ProductRequestDto(name, price));
        ProductResponseDto expectedProductDto = new ProductResponseDto(productId, name, price);

        // when, then
        assertThat(productDao.findProductById(productId)).usingRecursiveComparison().isEqualTo(expectedProductDto);
    }
}
