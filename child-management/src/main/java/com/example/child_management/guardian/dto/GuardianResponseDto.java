package com.example.child_management.guardian.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO used to send guardian information
 * back to the client.
 *
 * This prevents the API from exposing
 * the Guardian JPA entity directly.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuardianResponseDto {

    /**
     * Guardian ID.
     */
    private Long id;

    /**
     * Guardian first name.
     */
    private String firstName;

    /**
     * Guardian last name.
     */
    private String lastName;

    /**
     * Relationship with the child.
     */
    private String relationship;

    /**
     * Guardian mobile number.
     */
    private String mobile;

    /**
     * Guardian email address.
     */
    private String email;

    /**
     * Guardian address.
     */
    private String address;

    /**
     * Indicates whether the guardian is active.
     */
    private boolean active;

    /**
     * Guardian creation time.
     */
    private LocalDateTime createdAt;

    /**
     * Guardian last update time.
     */
    private LocalDateTime updatedAt;
}