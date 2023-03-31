package com.example.devcopassignment;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DivisibleByWeekValidator.class)
public @interface DivisibleByOneWeek {
    String message() default "Amount not divisible by the number of weeks.";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
    //int numberOfWeeks();
}
