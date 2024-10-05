package de.miladsa.behpaya.validators;

import de.miladsa.behpaya.model.HowToCalculate;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class HowToCalculateValidator {
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public Set<String> validator(HowToCalculate howToCalculate) {
        Set<ConstraintViolation<HowToCalculate>> violations = validator.validate(howToCalculate);
        if (!violations.isEmpty()) {
            return violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}
