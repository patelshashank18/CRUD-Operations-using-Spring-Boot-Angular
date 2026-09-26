package com.example.child_management.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for ChildCare360.
 *
 * This class handles application errors from all
 * REST controllers in one central location.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

        /**
         * Handles resource-not-found errors.
         *
         * Example:
         *
         * GET /api/children/999
         *
         * @param exception resource not found exception
         * @return standard error response
         */
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleResourceNotFound(
                        ResourceNotFoundException exception) {

                ErrorResponse response = new ErrorResponse(
                                false,
                                exception.getMessage(),
                                null,
                                LocalDateTime.now(),
                                null);

                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(response);
        }

        /**
         * Handles validation errors.
         *
         * Example:
         *
         * Empty first name
         * Invalid email
         * Invalid mobile number
         *
         * @param exception validation exception
         * @return standard validation error response
         */
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidationException(
                        MethodArgumentNotValidException exception) {

                Map<String, String> errors = new LinkedHashMap<>();

                exception.getBindingResult()
                                .getFieldErrors()
                                .forEach(error -> errors.put(
                                                error.getField(),
                                                error.getDefaultMessage()));

                ErrorResponse response = new ErrorResponse(
                                false,
                                "Request validation failed",
                                null,
                                LocalDateTime.now(),
                                errors);

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(response);
        }

        /**
         * Handles invalid request parameters.
         *
         * Examples:
         *
         * Invalid sort field
         * Invalid page number
         * Invalid page size
         * Invalid sorting direction
         *
         * @param exception illegal argument exception
         * @return standard bad request response
         */
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
                        IllegalArgumentException exception) {

                ErrorResponse response = new ErrorResponse(
                                false,
                                exception.getMessage(),
                                null,
                                LocalDateTime.now(),
                                null);

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(response);
        }
}