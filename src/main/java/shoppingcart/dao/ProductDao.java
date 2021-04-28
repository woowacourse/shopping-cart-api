package shoppingcart.dao;

import java.sql.PreparedStatement;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import shoppingcart.dto.ProductDto;

@Repository
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(ProductDto productDto) {
        final String query = "INSERT INTO product (name, price) VALUES (?, ?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                    final PreparedStatement preparedStatement =
                            connection.prepareStatement(query, new String[] {"id"});
                    preparedStatement.setString(1, productDto.getName());
                    preparedStatement.setInt(2, productDto.getPrice());
                    return preparedStatement;
                }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
