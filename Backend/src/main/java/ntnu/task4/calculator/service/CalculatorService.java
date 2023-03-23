package ntnu.task4.calculator.service;


import lombok.RequiredArgsConstructor;
import ntnu.task4.calculator.model.Equation;

import ntnu.task4.calculator.repository.EquationRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@EntityScan("ntnu.task4.calculator.model")
public class CalculatorService {

    @Autowired
    private final EquationRepository2 equationRepository;


    public Equation solve(Equation equation){
        switch(equation.getOperator()) {
            case "add":
                equation.setResult(equation.getLeftOperand() + equation.getRightOperand());
                break;
            case "subtract":
                equation.setResult(equation.getLeftOperand() - equation.getRightOperand());
                break;
            case "multiply":
                equation.setResult(equation.getLeftOperand() * equation.getRightOperand());
                break;
            case "divide":
                if (equation.getRightOperand() == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
                equation.setResult(equation.getLeftOperand() / equation.getRightOperand());
                break;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
        return equation;
    }

    public double add(Equation equation) {
        return equation.getLeftOperand() + equation.getRightOperand();
    }

    public double subtract(Equation equation) {
        return equation.getLeftOperand() - equation.getRightOperand();
    }

    public double multiply(Equation equation) {
        return equation.getLeftOperand() * equation.getRightOperand();
    }

    public double divide(Equation equation) {
        if (equation.getRightOperand() == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return equation.getLeftOperand() / equation.getRightOperand();
    }

    public List<Equation> getEquationsByUserId(Long userId){
        return this.equationRepository.findByUserId(userId);
    }

    public void saveEquation(Equation equation, Long userId){
        equationRepository.save(equation, userId);
    }

    public String toString(Equation equation) {
        String operatorSymbol;
        switch (equation.getOperator()) {
            case "add":
                operatorSymbol = "+";
                break;
            case "subtract":
                operatorSymbol = "-";
                break;
            case "multiply":
                operatorSymbol = "*";
                break;
            case "divide":
                operatorSymbol = "/";
                break;
            default:
                operatorSymbol = "?";
                break;
        }
        return equation.getLeftOperand() + " " + operatorSymbol + " " + equation.getRightOperand() + " = " + equation.getResult();
    }


}

