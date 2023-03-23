package ntnu.task4.calculator.repository;

import ntnu.task4.calculator.model.Equation;

import java.util.List;

public interface EquationRepository {
    List<Equation> findByUserId(Long userId);
    int save(Equation equation, Long userId);
}
