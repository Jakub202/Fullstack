package ntnu.task4.calculator.repository;

import ntnu.task4.calculator.model.Equation;

import java.util.List;

public interface EquationRepository2 {
    List<Equation> findByUserId(Long userId);
    int save(Equation equation, Long userId);
}
