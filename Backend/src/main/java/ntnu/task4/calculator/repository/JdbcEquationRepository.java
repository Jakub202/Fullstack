package ntnu.task4.calculator.repository;

import ntnu.task4.calculator.model.Equation;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcEquationRepository implements EquationRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcEquationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Equation> findByUserId(Long userId) {
        String query = "SELECT * FROM equation WHERE user_id = ?";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Equation.class), userId);
    }

    @Override
    public int save(Equation equation, Long userID) {
        String query = "INSERT INTO equation (left_operand, right_operand, operator, result, user_id) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query, equation.getLeftOperand(), equation.getRightOperand(), equation.getOperator(), equation.getResult(), userID);
    }
}
