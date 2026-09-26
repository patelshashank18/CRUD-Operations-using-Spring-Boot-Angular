package com.example.child_management.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Standard successful API response used by ChildCare360.
 *
 * This class keeps all successful API responses
 * in the same format.
 *
 * Example:
 *
 * {
 * "success": true,
 * "message": "Child created successfully",
 * "data": {
 * "id": 1,
 * "firstName": "Rahul"
 * },
 * "timestamp": "2026-09-14T18:00:00"
 * }
 *
 * @param <T> type of data returned by the API
 */
@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    /**
     * Indicates whether the request was successful.
     *
     * Successful API requests return true.
     */
    private boolean success;

    /**
     * Human-readable message describing the result.
     */
    private String message;

    /**
     * Actual data returned by the API.
     *
     * The type can be ChildDto, List, PaginationResponse,
     * or any other object.
     */
    private T data;

    /**
     * Date and time when the response was created.
     */
    private LocalDateTime timestamp;
}
