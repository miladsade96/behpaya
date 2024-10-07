package de.miladsa.behpaya.validators;

import de.miladsa.behpaya.model.Type;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class TypeValidator implements ConstraintValidator<ValidType, Type> {

    @Override
    public void initialize(ValidType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Type type, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (type == null) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Provide Type")
                    .addConstraintViolation();
            return false;
        }

        if (!Arrays.asList(Type.values()).contains(type)) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Invalid Type")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
