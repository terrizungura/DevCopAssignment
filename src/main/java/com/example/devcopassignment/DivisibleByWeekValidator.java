package com.example.devcopassignment;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivisibleByWeekValidator implements ConstraintValidator<DivisibleByOneWeek, String> {

    //private int numberOfWeeks;

    @Override
    public void initialize(DivisibleByOneWeek constraintAnnotation) {
        // this.numberOfWeeks  = constraintAnnotation.numberOfWeeks();
    }

    @Override
    public boolean isValid(String amount, ConstraintValidatorContext context) {
        if (amount == null || amount.trim().isEmpty()) {
            return true;
        }

        BigDecimal weeklyAmount = null;
        int weeks = 1;

        // determine the number of weeks based on the frequency
        Frequency frequency = Frequency.WEEK; // default value
        if (context instanceof HibernateConstraintValidatorContext) {
            HibernateConstraintValidatorContext hibernateContext = (HibernateConstraintValidatorContext) context;
            Object parent = hibernateContext.getDefaultConstraintMessageTemplate();
            if (parent instanceof RegularAmount) {
                frequency = ((RegularAmount) parent).getFrequency();
            }
        }

        switch (frequency) {
            case WEEK:
                weeklyAmount = new BigDecimal(amount);
                break;

            case TWO_WEEK:
                weeks = 2;
                break;

            case FOUR_WEEK:
                weeks = 4;
                break;

            case MONTH:
                return true; //Monthly cannot be divided into weekly amounts

            case QUARTER:
                weeks = 13;
                break;

            case YEAR:
                weeks = 52;
                break;
            default:
                throw new IllegalArgumentException("Unknown frequency: " + frequency);
        }

        if (weeklyAmount == null) {
            //Calculate the weekly amount if needed
            BigDecimal totalAmount = new BigDecimal(amount);
            weeklyAmount = totalAmount.divide(new BigDecimal(weeks), 2, RoundingMode.DOWN);
        }

        BigDecimal weeklyPence = weeklyAmount.multiply(new BigDecimal("100")).setScale(0, RoundingMode.DOWN);

        //Checking if the weekly amount is a whole number of pence
        return weeklyAmount.multiply(new BigDecimal(100)).remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0;
    }
}



