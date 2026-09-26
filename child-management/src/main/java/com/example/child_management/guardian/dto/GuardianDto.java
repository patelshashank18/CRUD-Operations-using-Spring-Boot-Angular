package com.example.child_management.guardian.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO used to receive Guardian information
 * from the REST API.
 *
 * DTO keeps API input separate from
 * the database Entity.
 */
@Getter
@Setter
public class GuardianDto {

    /**
     * Guardian first name.
     */
    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name must not exceed 100 characters")
    private String firstName;

    /**
     * Guardian last name.
     */
    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    private String lastName;

    /**
     * Relationship with the child.
     */
    @NotBlank(message = "Relationship is required")
    @Size(max = 50, message = "Relationship must not exceed 50 characters")
    private String relationship;

    /**
     * Guardian mobile number.
     */
    @NotBlank(message = "Mobile number is required")
    @Size(max = 20, message = "Mobile number must not exceed 20 characters")
    private String mobile;

    /**
     * Guardian email address.
     */
    @Email(message = "Please provide a valid email address")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    private String email;

    /**
     * Guardian address.
     */
    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;

    /**
     * Indicates whether the guardian is active.
     */
    private boolean active = true;
}