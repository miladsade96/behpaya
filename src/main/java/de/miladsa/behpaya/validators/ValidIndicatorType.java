package de.miladsa.behpaya.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IndicatorTypeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIndicatorType {
    String message() default "Invalid Indicator Type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
