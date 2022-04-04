package com.example.xmenmutants.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DnaListValidator.class)
public @interface DnaListValidation {
    public String message() default "Invalid DNA list: must be A,C,G or T";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}