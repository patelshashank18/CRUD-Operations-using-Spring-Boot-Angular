package com.example.child_management.controller;

import java.time.LocalDateTime;
import java.util.List;

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

import com.example.child_management.emergency.dto.EmergencyContactDto;
import com.example.child_management.emergency.dto.EmergencyContactResponseDto;
import com.example.child_management.emergency.service.EmergencyContactService;
import com.example.child_management.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST Controller for Emergency Contact management.
 *
 * Base URL:
 * /api/children/{childId}/emergency-contacts
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class EmergencyContactController {

    private final EmergencyContactService emergencyContactService;

    /**
     * Creates an emergency contact for a child.
     */
    @PostMapping("/children/{childId}/emergency-contacts")
    public ResponseEntity<ApiResponse<EmergencyContactResponseDto>> createContact(
            @PathVariable Long childId,
            @Valid @RequestBody EmergencyContactDto dto) {

        EmergencyContactResponseDto response = emergencyContactService.createContact(
                childId,
                dto);

        ApiResponse<EmergencyContactResponseDto> apiResponse = new ApiResponse<>(
                true,
                "Emergency contact created successfully",
                response,
                LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }

    /**
     * Gets all emergency contacts for a child.
     */
    @GetMapping("/children/{childId}/emergency-contacts")
    public ResponseEntity<ApiResponse<List<EmergencyContactResponseDto>>> getContacts(
            @PathVariable Long childId) {

        List<EmergencyContactResponseDto> response = emergencyContactService
                .getContactsByChildId(childId);

        ApiResponse<List<EmergencyContactResponseDto>> apiResponse = new ApiResponse<>(
                true,
                "Emergency contacts retrieved successfully",
                response,
                LocalDateTime.now());

        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Gets one emergency contact by ID.
     */
    @GetMapping("/emergency-contacts/{id}")
    public ResponseEntity<ApiResponse<EmergencyContactResponseDto>> getContact(
            @PathVariable Long id) {

        EmergencyContactResponseDto response = emergencyContactService
                .getContactById(id);

        ApiResponse<EmergencyContactResponseDto> apiResponse = new ApiResponse<>(
                true,
                "Emergency contact retrieved successfully",
                response,
                LocalDateTime.now());

        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Updates an emergency contact.
     */
    @PutMapping("/emergency-contacts/{id}")
    public ResponseEntity<ApiResponse<EmergencyContactResponseDto>> updateContact(
            @PathVariable Long id,
            @Valid @RequestBody EmergencyContactDto dto) {

        EmergencyContactResponseDto response = emergencyContactService
                .updateContact(id, dto);

        ApiResponse<EmergencyContactResponseDto> apiResponse = new ApiResponse<>(
                true,
                "Emergency contact updated successfully",
                response,
                LocalDateTime.now());

        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Deletes an emergency contact.
     */
    @DeleteMapping("/emergency-contacts/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteContact(
            @PathVariable Long id) {

        emergencyContactService.deleteContact(id);

        ApiResponse<Void> apiResponse = new ApiResponse<>(
                true,
                "Emergency contact deleted successfully",
                null,
                LocalDateTime.now());

        return ResponseEntity.ok(apiResponse);
    }
}
