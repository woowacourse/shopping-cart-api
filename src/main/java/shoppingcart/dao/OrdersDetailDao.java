package shoppingcart.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * create table orders_detail
 * (
 *     id         bigint  not null auto_increment,
 *     orders_id  bigint  not null,
 *     product_id bigint  not null,
 *     quantity   integer not null,
 *     primary key (id)
 * ) engine=InnoDB default charset=utf8mb4;
 */

@Repository
public class OrdersDetailDao {
    private final JdbcTemplate jdbcTemplate;

    public OrdersDetailDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long addOrdersDetail(long ordersId, long customerId, long productId, int quantity) {
        String sql = "INSERT INTO orders_detail (orders_id, customer_id, product_id, quantity) VALUES (?, ?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setLong(1, ordersId);
            preparedStatement.setLong(2, customerId);
            preparedStatement.setLong(3, productId);
            preparedStatement.setLong(4, quantity);
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<OrdersDetailDto> findOrdersDetailsByOrderId(long orderId) {
        String sql = "SELECT product_id, quantity FROM orders_detail WHERE order_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new OrdersDetailDto(
                rs.getLong("product_id"),
                rs.getInt("quantity")
        ));
    }
}