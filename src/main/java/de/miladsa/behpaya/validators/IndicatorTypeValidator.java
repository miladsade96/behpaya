package de.miladsa.behpaya.validators;

import de.miladsa.behpaya.model.IndicatorType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;


public class IndicatorTypeValidator implements ConstraintValidator<ValidIndicatorType, IndicatorType> {

    @Override
    public void initialize(ValidIndicatorType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(IndicatorType indicatorType, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (indicatorType == null) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Provide indicatorType")
                    .addConstraintViolation();
            return false;
        }

        if (!Arrays.asList(IndicatorType.values()).contains(indicatorType)) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Invalid indicatorType")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
