package de.miladsa.behpaya.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UnitValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUnit {
    String message() default "Invalid Unit";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
