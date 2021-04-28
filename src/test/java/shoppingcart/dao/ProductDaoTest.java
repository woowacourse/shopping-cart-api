package shoppingcart.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestConstructor;

import static org.assertj.core.api.Assertions.*;

import shoppingcart.dto.ProductDto;

@JdbcTest
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
        Long productId = productDao.save(new ProductDto(name, price));

        // then
        assertThat(productId).isEqualTo(1L);
    }
}
