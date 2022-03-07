package com.aula04.banco.banco.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PASSWORDValidator implements ConstraintValidator<PASSWORD, String> {
    @Override
    public void initialize(PASSWORD constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        boolean isLengthValid = password.length() > 5;
        boolean containUpperCase = containsCaseCharacter(password, true);
        boolean containLowerCase = containsCaseCharacter(password, false);
        boolean hasNumber = password.matches(".*\\d.*");

        return isLengthValid && containUpperCase && containLowerCase && hasNumber;
    }

    private boolean containsCaseCharacter(String string, boolean upper) {
        for (int i = 0; i < string.length(); i++) {
            if(upper) {
                if (Character.isUpperCase(string.charAt(i))) {
                    return true;
                }
            }else{
                if (Character.isLowerCase(string.charAt(i))) {
                    return true;
                }
            }
        }

        return false;
    }
}
