package com.example.xmenmutants.validator;


import java.util.Arrays;
import java.util.HashSet;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class DnaListValidator implements ConstraintValidator<DnaListValidation, String[]> {

    private static final HashSet<String> VALID_CHARS = new HashSet<>(Arrays.asList("A", "C", "G", "T"));
    @Override
    public boolean isValid(String[] values, ConstraintValidatorContext context) {
        for (String currentRow : values) {
            //currentChar = "ACGT";
            for(int i = 0; i< currentRow.length(); i ++){
                var currentChar = currentRow.charAt(i);
                if(!VALID_CHARS.contains(String.valueOf(currentChar))){
                    return false;
                }
            }
        }
        return true;
    }
}
