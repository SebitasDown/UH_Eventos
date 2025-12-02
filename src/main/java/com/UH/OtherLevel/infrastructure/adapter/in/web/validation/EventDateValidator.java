package com.UH.OtherLevel.infrastructure.adapter.in.web.validation;

import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.CreateEventRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.UpdateEventRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

/**
 * Validates that the event start date is before the end date.
 */
public class EventDateValidator implements ConstraintValidator<ValidEventDates, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // let @NotNull handle this case
        }

        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        if (value instanceof CreateEventRequest request) {
            startDate = request.getDate();
            endDate = request.getEndDate();
        } else if (value instanceof UpdateEventRequest request) {
            startDate = request.getDate();
            endDate = request.getEndDate();
        }

        if (startDate == null || endDate == null) {
            return true; // other constraints cover required fields
        }

        boolean isValid = startDate.isBefore(endDate);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("endDate")
                    .addConstraintViolation();
        }

        return isValid;
    }
}

