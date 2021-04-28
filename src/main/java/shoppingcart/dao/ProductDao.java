package shoppingcart.dao;

import java.sql.PreparedStatement;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import shoppingcart.dto.ProductRequestDto;
import shoppingcart.dto.ProductResponseDto;

@Repository
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(ProductRequestDto productRequestDto) {
        final String query = "INSERT INTO product (name, price) VALUES (?, ?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement =
                    connection.prepareStatement(query, new String[] {"id"});
            preparedStatement.setString(1, productRequestDto.getName());
            preparedStatement.setInt(2, productRequestDto.getPrice());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public ProductResponseDto findProductById(Long productId) {
        final String query = "SELECT name, price FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(query,
                (resultSet, rowNumber) ->
                        new ProductResponseDto(productId,
                                resultSet.getString("name"), resultSet.getInt("price")
                        ), productId
        );
    }
}
