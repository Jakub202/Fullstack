package ntnu.task4.calculator.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

    @Repository
    public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public long getUserIdByName(String name) {
        String sql = "SELECT id FROM user WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, Long.class);
    }

}
