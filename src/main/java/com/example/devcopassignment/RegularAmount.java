package com.example.devcopassignment;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class RegularAmount {

    @NotNull(message = "Frequency cannot be null")
    private Frequency frequency;

    @DivisibleByOneWeek(message = "Amount must be divisible by one week's worth of pence for the selected frequency")
    private String amount;

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

enum Frequency{
    WEEK, TWO_WEEK, FOUR_WEEK, MONTH, QUARTER, YEAR;
}
