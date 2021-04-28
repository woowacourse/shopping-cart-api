package shoppingcart.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * 1. 장바구니 조회
 * <p>
 * <p>
 * create table cart
 * (
 * id          bigint not null auto_increment,
 * customer_id bigint not null,
 * product_id  bigint not null
 * );
 */

@Repository
public class CartDao {
    private final JdbcTemplate jdbcTemplate;

    public CartDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Long> findProductIdsByCustomerId(long customerId) {
        String sql = "SELECT product_id FROM cart WHERE customer_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getLong("product_id"), customerId);
    }

    public List<Long> findIdsByCustomerId(long customerId) {
        String sql = "SELECT id FROM cart WHERE customer_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getLong("id"), customerId);
    }

    public Long addCartItem(long customerId, long productId) {
        String sql = "INSERT INTO CART(customer_id, product_id) VALUES(?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setLong(1, customerId);
            preparedStatement.setLong(2, productId);
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void deleteCartItem(long id) {
        String sql = "DELETE FROM cart WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }
}
