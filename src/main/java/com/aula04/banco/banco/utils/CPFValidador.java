package com.aula04.banco.banco.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CPFValidador implements ConstraintValidator<CPF, String> {

    @Override
    public void initialize(CPF constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        String cpfSomenteDigitos = cpf.replaceAll("\\D", "");

        if(cpfSomenteDigitos == null ||
                cpfSomenteDigitos.length() != 11 ||
                cpfSomenteDigitos.equals("00000000000") ||
                cpfSomenteDigitos.equals("11111111111")){return  false;}
        return true;
    }
}
