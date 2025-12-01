package com.UH.OtherLevel.infrastructure.adapter.in.web.exception;

import com.UH.OtherLevel.domain.exceptions.eventExceptions.EventNotFoundException;
import com.UH.OtherLevel.domain.exceptions.eventExceptions.InvalidEventDateException;
import com.UH.OtherLevel.domain.exceptions.venueExceptions.VenueNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Validation Error");
        problem.setDetail("There are validation errors in the submitted data");
        problem.setInstance(URI.create(request.getRequestURI()));

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        problem.setProperty("errors", errors);

        return enrichProblemDetail(problem);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ProblemDetail handleEventNotFound(EventNotFoundException ex, HttpServletRequest request) {
        return createProblemDetail(HttpStatus.NOT_FOUND, "Event Not Found", ex.getMessage(), request);
    }

    @ExceptionHandler(InvalidEventDateException.class)
    public ProblemDetail handleInvalidEventDate(InvalidEventDateException ex, HttpServletRequest request) {
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Invalid Event Date", ex.getMessage(), request);
    }

    @ExceptionHandler(VenueNotFoundException.class)
    public ProblemDetail handleVenueNotFound(VenueNotFoundException ex, HttpServletRequest request) {
        return createProblemDetail(HttpStatus.NOT_FOUND, "Venue Not Found", ex.getMessage(), request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        return createProblemDetail(HttpStatus.NOT_FOUND, "Entity Not Found", ex.getMessage(), request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        return createProblemDetail(HttpStatus.CONFLICT, "Data Integrity Violation",
                "A database constraint was violated.", request);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception ex, HttpServletRequest request) {
        return createProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
                "An unexpected error occurred.", request);
    }

    private ProblemDetail createProblemDetail(HttpStatus status, String title, String detail,
            HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setTitle(title);
        problem.setDetail(detail);
        problem.setInstance(URI.create(request.getRequestURI()));
        return enrichProblemDetail(problem);
    }

    private ProblemDetail enrichProblemDetail(ProblemDetail problem) {
        problem.setProperty("timestamp", LocalDateTime.now());
        problem.setProperty("traceId", UUID.randomUUID().toString());
        return problem;
    }
}
