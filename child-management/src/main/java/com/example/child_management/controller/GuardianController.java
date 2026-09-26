package com.example.child_management.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.child_management.guardian.dto.GuardianDto;
import com.example.child_management.guardian.dto.GuardianResponseDto;
import com.example.child_management.guardian.service.GuardianService;
import com.example.child_management.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST Controller for Guardian management.
 *
 * Provides APIs for creating, retrieving,
 * updating, and deleting guardians.
 *
 * Base URL:
 * /api/guardians
 */
@RestController
@RequestMapping("/api/guardians")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class GuardianController {

        private final GuardianService guardianService;

        /**
         * Creates a new guardian.
         *
         * @param dto guardian information
         * @return created guardian
         */
        @PostMapping
        public ResponseEntity<ApiResponse<GuardianResponseDto>> createGuardian(
                        @Valid @RequestBody GuardianDto dto) {

                GuardianResponseDto response = guardianService.createGuardian(dto);

                ApiResponse<GuardianResponseDto> apiResponse = new ApiResponse<>(
                                true,
                                "Guardian created successfully",
                                response,
                                LocalDateTime.now());

                return ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(apiResponse);
        }

        /**
         * Gets all guardians.
         *
         * No pagination or sorting is used
         * for Guardian management.
         *
         * @return list of guardians
         */
        @GetMapping
        public ResponseEntity<ApiResponse<java.util.List<GuardianResponseDto>>> getAllGuardians() {

                java.util.List<GuardianResponseDto> response = guardianService.getAllGuardians();

                ApiResponse<java.util.List<GuardianResponseDto>> apiResponse = new ApiResponse<>(
                                true,
                                "Guardians retrieved successfully",
                                response,
                                LocalDateTime.now());

                return ResponseEntity.ok(apiResponse);
        }

        /**
         * Gets one guardian by ID.
         *
         * @param id guardian ID
         * @return guardian information
         */
        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<GuardianResponseDto>> getGuardianById(
                        @PathVariable Long id) {

                GuardianResponseDto response = guardianService.getGuardianById(id);

                ApiResponse<GuardianResponseDto> apiResponse = new ApiResponse<>(
                                true,
                                "Guardian retrieved successfully",
                                response,
                                LocalDateTime.now());

                return ResponseEntity.ok(apiResponse);
        }

        /**
         * Updates an existing guardian.
         *
         * @param id  guardian ID
         * @param dto updated guardian information
         * @return updated guardian
         */
        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<GuardianResponseDto>> updateGuardian(
                        @PathVariable Long id,
                        @Valid @RequestBody GuardianDto dto) {

                GuardianResponseDto response = guardianService.updateGuardian(id, dto);

                ApiResponse<GuardianResponseDto> apiResponse = new ApiResponse<>(
                                true,
                                "Guardian updated successfully",
                                response,
                                LocalDateTime.now());

                return ResponseEntity.ok(apiResponse);
        }

        /**
         * Deletes a guardian.
         *
         * @param id guardian ID
         * @return success response
         */
        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> deleteGuardian(
                        @PathVariable Long id) {

                guardianService.deleteGuardian(id);

                ApiResponse<Void> apiResponse = new ApiResponse<>(
                                true,
                                "Guardian deleted successfully",
                                null,
                                LocalDateTime.now());

                return ResponseEntity.ok(apiResponse);
        }
}