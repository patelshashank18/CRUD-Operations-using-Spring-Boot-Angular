package com.example.child_management.relationship.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO used to return information about a
 * guardian connected to a child.
 *
 * This prevents the REST API from directly
 * exposing the Guardian JPA entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChildGuardianResponseDto {

    /**
     * Guardian ID.
     */
    private Long guardianId;

    /**
     * Guardian first name.
     */
    private String firstName;

    /**
     * Guardian last name.
     */
    private String lastName;

    /**
     * Relationship between guardian and child.
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
     * Indicates whether the guardian is active.
     */
    private boolean active;
}