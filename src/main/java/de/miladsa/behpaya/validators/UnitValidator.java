package de.miladsa.behpaya.validators;

import de.miladsa.behpaya.model.Unit;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class UnitValidator implements ConstraintValidator<ValidUnit, Unit> {

    @Override
    public void initialize(ValidUnit constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Unit unit, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (unit == null) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Provide Unit")
                    .addConstraintViolation();
            return false;
        }

        if (!Arrays.asList(Unit.values()).contains(unit)) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Invalid Unit")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
