package shoppingcart.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    public CustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long findIdByUserName(String userName) {
        final String query = "SELECT id FROM CUSTOMER WHERE username = ?";
        return jdbcTemplate.queryForObject(query, Long.class, userName);
    }
}
