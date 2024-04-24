package com.reto.obardales.exception;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationValidationException extends RuntimeException {

    private final Set<ConstraintViolation<Object>> violations;

    private static final String VIOLATION_MESSAGE = "Review the following observations.";

    public ApplicationValidationException(Set<ConstraintViolation<Object>> violations) {
        super(VIOLATION_MESSAGE);
        this.violations = violations;
    }

    public ApplicationValidationException(String message, Set<ConstraintViolation<Object>> violations) {
        super(message);
        this.violations = violations;
    }

    public List<String> errors() {
        return this.violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }



}
