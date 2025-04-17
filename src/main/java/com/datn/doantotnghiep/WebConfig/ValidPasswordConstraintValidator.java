package com.datn.doantotnghiep.WebConfig;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidPasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Autowired
    private PasswordValidator passwordValidator;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        boolean isValid = passwordValidator.isValid(password);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(passwordValidator.getPasswordRequirementsMessage())
                    .addConstraintViolation();
        }

        return isValid;
    }


}
