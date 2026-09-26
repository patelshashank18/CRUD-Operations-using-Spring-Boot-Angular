package com.example.child_management.relationship.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.child_management.relationship.dto.ChildGuardianResponseDto;
import com.example.child_management.relationship.service.ChildGuardianService;
import com.example.child_management.response.ApiResponse;

import lombok.RequiredArgsConstructor;

/**
 * REST Controller for managing the relationship
 * between Children and Guardians.
 *
 * Base URL:
 * /api/children
 *
 * Examples:
 *
 * POST
 * /api/children/10/guardians/1
 *
 * GET
 * /api/children/10/guardians
 *
 * DELETE
 * /api/children/10/guardians/1
 */
@RestController
@RequestMapping("/api/children")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ChildGuardianController {

    /**
     * Service responsible for
     * Child-Guardian business logic.
     */
    private final ChildGuardianService childGuardianService;

    /**
     * Connects a guardian to a child.
     *
     * HTTP:
     * POST
     *
     * Example:
     *
     * POST /api/children/10/guardians/1
     *
     * @param childId    child ID
     * @param guardianId guardian ID
     * @return successful API response
     */
    @PostMapping("/{childId}/guardians/{guardianId}")
    public ResponseEntity<ApiResponse<Void>> addGuardianToChild(
            @PathVariable Long childId,
            @PathVariable Long guardianId) {

        /**
         * Ask the Service layer to create
         * the Child-Guardian relationship.
         */
        childGuardianService.addGuardianToChild(
                childId,
                guardianId);

        /**
         * Build the standard API response.
         *
         * There is no object to return after
         * creating the relationship, so data is null.
         */
        ApiResponse<Void> response = new ApiResponse<>(
                true,
                "Guardian connected to child successfully",
                null,
                LocalDateTime.now());

        /**
         * HTTP 201 means the relationship
         * was successfully created.
         */
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Gets all guardians connected to a child.
     *
     * HTTP:
     * GET
     *
     * Example:
     *
     * GET /api/children/10/guardians
     *
     * @param childId child ID
     * @return list of connected guardians
     */
    @GetMapping("/{childId}/guardians")
    public ResponseEntity<ApiResponse<List<ChildGuardianResponseDto>>> getGuardiansByChild(
            @PathVariable Long childId) {

        /**
         * Get guardian DTOs from the Service layer.
         */
        List<ChildGuardianResponseDto> guardians = childGuardianService
                .getGuardiansByChildId(childId);

        /**
         * Wrap the list inside the standard
         * ApiResponse structure.
         */
        ApiResponse<List<ChildGuardianResponseDto>> response = new ApiResponse<>(
                true,
                "Guardians retrieved successfully",
                guardians,
                LocalDateTime.now());

        return ResponseEntity.ok(response);
    }

    /**
     * Removes a guardian from a child.
     *
     * HTTP:
     * DELETE
     *
     * Example:
     *
     * DELETE /api/children/10/guardians/1
     *
     * @param childId    child ID
     * @param guardianId guardian ID
     * @return successful API response
     */
    @DeleteMapping("/{childId}/guardians/{guardianId}")
    public ResponseEntity<ApiResponse<Void>> removeGuardianFromChild(
            @PathVariable Long childId,
            @PathVariable Long guardianId) {

        /**
         * Ask the Service layer to remove
         * the relationship.
         */
        childGuardianService.removeGuardianFromChild(
                childId,
                guardianId);

        /**
         * Build the standard response.
         */
        ApiResponse<Void> response = new ApiResponse<>(
                true,
                "Guardian removed from child successfully",
                null,
                LocalDateTime.now());

        return ResponseEntity.ok(response);
    }
}
