package org.maxkizi.userservice.validator;

import lombok.extern.slf4j.Slf4j;
import org.maxkizi.userservice.annotation.ValidBirthDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class BirthDateValidator implements ConstraintValidator<ValidBirthDate, String> {
    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd.MM.yyyy")).compareTo(LocalDate.now()) <= 0;
        } catch (DateTimeParseException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
