package com.example.child_management.exception;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Standard error response used by ChildCare360.
 *
 * This class keeps all API error responses
 * in one consistent format.
 *
 * Example:
 *
 * {
 * "success": false,
 * "message": "Child not found with id: 999",
 * "data": null,
 * "timestamp": "2026-09-14T18:30:00",
 * "errors": null
 * }
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {

    /**
     * Indicates whether the request was successful.
     *
     * For errors this value is always false.
     */
    private boolean success;

    /**
     * Human-readable error message.
     */
    private String message;

    /**
     * Additional error data.
     *
     * Usually null for normal errors.
     */
    private Object data;

    /**
     * Date and time when the error occurred.
     */
    private LocalDateTime timestamp;

    /**
     * Field-level validation errors.
     *
     * Example:
     *
     * {
     * "firstName": "First name is required",
     * "email": "Invalid email"
     * }
     */
    private Map<String, String> errors;
}