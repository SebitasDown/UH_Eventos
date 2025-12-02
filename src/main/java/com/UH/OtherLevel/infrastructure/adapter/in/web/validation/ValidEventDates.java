package com.UH.OtherLevel.infrastructure.adapter.in.web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Cross-field validation to ensure event start date is before end date.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EventDateValidator.class)
@Documented
public @interface ValidEventDates {

    String message() default "{event.dates.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

