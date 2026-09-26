package com.example.child_management.emergency.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO used to receive emergency contact information
 * from the REST API.
 */
@Getter
@Setter
public class EmergencyContactDto {

    /**
     * Emergency contact name.
     */
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    /**
     * Relationship with the child.
     */
    @NotBlank(message = "Relationship is required")
    @Size(max = 50, message = "Relationship must not exceed 50 characters")
    private String relationship;

    /**
     * Primary mobile number.
     */
    @NotBlank(message = "Mobile number is required")
    @Size(max = 20, message = "Mobile number must not exceed 20 characters")
    private String mobile;

    /**
     * Alternative mobile number.
     */
    @Size(max = 20, message = "Alternate mobile must not exceed 20 characters")
    private String alternateMobile;

    /**
     * Email address.
     */
    @Email(message = "Please provide a valid email address")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    private String email;

    /**
     * Address of the emergency contact.
     */
    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;

    /**
     * Priority of the emergency contact.
     */
    @NotNull(message = "Priority is required")
    private Integer priority = 1;

    /**
     * Indicates whether the contact is active.
     */
    private boolean active = true;
}
