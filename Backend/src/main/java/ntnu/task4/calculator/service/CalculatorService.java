package ntnu.task4.calculator.service;


import ntnu.task4.calculator.model.Equation;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

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

    public String toString(Equation equation, double result) {
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
        return equation.getLeftOperand() + " " + operatorSymbol + " " + equation.getRightOperand() + " = " + result;
    }


}

