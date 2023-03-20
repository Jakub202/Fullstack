package ntnu.task4.calculator.controller;

import ntnu.task4.calculator.model.Equation;
import ntnu.task4.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);
    @PostMapping("/{operator}")
    public ResponseEntity<Double> calculate(@RequestBody Equation equation,
                                            @PathVariable String operator) {
        Double result = 0.0;
        switch (operator) {
            case "add":
                result = calculatorService.add(equation);
                equation.setOperator("add");
                break;
            case "subtract":
                result = calculatorService.subtract(equation);
                equation.setOperator("subtract");
                break;
            case "multiply":
                result = calculatorService.multiply(equation);
                equation.setOperator("multiply");
                break;
            case "divide":
                result = calculatorService.divide(equation);
                equation.setOperator("divide");
                break;
            default:
                logger.error("Could not resolve equation");
                return ResponseEntity.badRequest().build();
        }
        logger.info("Calculation: " + calculatorService.toString(equation,result));
        return ResponseEntity.ok().body(result);
    }
}