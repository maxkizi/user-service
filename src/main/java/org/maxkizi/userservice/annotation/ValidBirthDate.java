package org.maxkizi.userservice.annotation;

import org.maxkizi.userservice.validator.BirthDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = BirthDateValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface ValidBirthDate {
    String message() default "{org.maxkizi.userservice.annotation.ValidBirthDate.message}";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
