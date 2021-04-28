package shoppingcart.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * create table orders
 * (
 *     id          bigint not null auto_increment,
 *     customer_id bigint not null,
 *     primary key (id)
 * ) engine=InnoDB default charset=utf8mb4;
 */
@Repository
public class OrderDao {
    private JdbcTemplate jdbcTemplate;

    public OrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // return : order_id
    public Long addOrders(long customerId){
        String sql = "INSERT INTO orders (customer_id) VALUES (?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setLong(1, customerId);
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<Long> findOrderIdsByCustomerId(long customerId){
        String sql = "SELECT id FROM orders WHERE customer_id = ? ";

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getLong("id"), customerId);
    }
}
