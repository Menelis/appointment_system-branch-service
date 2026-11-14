package co.appointment.exception;

import co.appointment.shared.dto.ServiceResponseDto;
import co.appointment.shared.exception.ServiceException;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(final Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.internalServerError()
                .body(ServiceResponseDto.builder()
                        .success(false)
                        .responseMessage(exception.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleServiceException(final ServiceException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(exception.getStatus()).body(exception.getResponse());
    }

    // 1. Handle @Valid failures on @RequestBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "One of more fields failed validation.");
        problemDetail.setTitle("Validation Error");

        Map<String, String> fieldErrors = new HashMap<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(error -> fieldErrors.putIfAbsent(error.getField(), error.getDefaultMessage()));

        problemDetail.setProperty("errors", fieldErrors);

        return problemDetail;
    }

    // 2. Handles method parameter validation (@RequestParam, @PathVariable)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidationException(final HandlerMethodValidationException exception) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request parameter validation failed.");
        problemDetail.setTitle("Parameter Validation Error");

        Map<String, String> errors = new HashMap<>();
        exception.getParameterValidationResults().forEach(result -> {
            String paramName = result.getMethodParameter().getParameterName();

            result.getResolvableErrors().forEach(err -> errors.putIfAbsent(paramName, err.getDefaultMessage()));
        });

        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    // 3. Handles @Validated service-layer or class-level constraint violations
    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(final ConstraintViolationException exception) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Constraint violation occurred.");
        problemDetail.setTitle("Constraint Violation");

        Map<String, String> errors = new HashMap<>();
        exception
                .getConstraintViolations()
                .forEach(error -> errors.putIfAbsent(error.getPropertyPath().toString(), error.getMessage()));

        problemDetail.setProperty("error", errors);

        return problemDetail;
    }
}
