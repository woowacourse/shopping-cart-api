package shoppingcart.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import shoppingcart.dto.Product;
import shoppingcart.dto.ProductDto;

@Repository
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public ProductDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(final ProductDto productDto) {
        final String query = "INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement =
                    connection.prepareStatement(query, new String[]{"id"});
            preparedStatement.setString(1, productDto.getName());
            preparedStatement.setInt(2, productDto.getPrice());
            preparedStatement.setString(3, productDto.getImageUrl());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Product findProductById(final Long productId) {
        final String query = "SELECT name, price, image_url FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(query, (resultSet, rowNumber) ->
                new Product(
                        productId,
                        resultSet.getString("name"), resultSet.getInt("price"),
                        resultSet.getString("image_url")
                ), productId
        );
    }

    public List<Product> findProducts() {
        final String query = "SELECT id, name, price, image_url FROM product";
        return jdbcTemplate.query(query,
                (resultSet, rowNumber) ->
                        new Product(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("price"),
                                resultSet.getString("image_url")
                        ));
    }

    public void delete(final Long productId) {
        final String query = "DELETE FROM PRODUCT WHERE id = ?";
        jdbcTemplate.update(query, productId);
    }

//    public List<Product> findProductsByIds(final List<Long> productIds) {
//        return productIds.stream()
//                .map(this::findProductById)
//                .collect(Collectors.toList());
//    }
}
