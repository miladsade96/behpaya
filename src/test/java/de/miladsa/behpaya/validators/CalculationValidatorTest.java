package de.miladsa.behpaya.validators;

import de.miladsa.behpaya.model.Calculation;
import de.miladsa.behpaya.model.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class CalculationValidatorTest {
    private CalculationValidator calculationValidator;

    @Autowired
    public void setCalculationValidator(CalculationValidator calculationValidator) {
        this.calculationValidator = calculationValidator;
    }

    @Test
    void testValidatorForAValidCalculation() {
        Calculation calculation = new Calculation();
        calculation.setCommand("This is a command");
        calculation.setDescription("This is a description");
        Document document = new Document();
        document.setTitle("document title");
        calculation.setDocument(document);
        var result = calculationValidator.validator(calculation);
        Assertions.assertEquals(Collections.emptySet(), result);
    }

    @Test
    void testValidatorForAnInvalidCalculation() {
        Calculation calculation = new Calculation();
        calculation.setCommand("");
        var result = calculationValidator.validator(calculation);
        Assertions.assertFalse(result.isEmpty());
    }
}
