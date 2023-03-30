package ntnu.task4.calculator.controller;

import ntnu.task4.calculator.model.Equation;
import ntnu.task4.calculator.service.CalculatorService;
import ntnu.task4.calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    //TODO: add token auth
    @PostMapping("/solve")
    public ResponseEntity<Double> calculate(@RequestBody Equation equation) {
        try{
        calculatorService.solve(equation);
        }catch (Error e){
            logger.error("Could not resolve equation");
            return ResponseEntity.badRequest().build();
        }
        calculatorService.saveEquation(equation, 1L);
        logger.info("Calculation: " + calculatorService.toString(equation));
        return ResponseEntity.ok().body(equation.getResult());
    }


    @GetMapping("/history")
    public ResponseEntity<List<Equation>> getHistory(@RequestParam("userName") String userName){

        long id = userService.getUserIdByName(userName);

        return ResponseEntity.ok().body(calculatorService.getEquationsByUserId(id));

    }


}