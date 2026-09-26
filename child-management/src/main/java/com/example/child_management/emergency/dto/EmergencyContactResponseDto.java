package com.example.child_management.emergency.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO used to send emergency contact information
 * back to the client.
 *
 * This DTO prevents the API from exposing the
 * complete Child JPA entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContactResponseDto {

    /**
     * Emergency contact ID.
     */
    private Long id;

    /**
     * ID of the child.
     */
    private Long childId;

    /**
     * Full name of the child.
     */
    private String childName;

    /**
     * Emergency contact name.
     */
    private String name;

    /**
     * Relationship with the child.
     */
    private String relationship;

    /**
     * Primary mobile number.
     */
    private String mobile;

    /**
     * Alternative mobile number.
     */
    private String alternateMobile;

    /**
     * Email address.
     */
    private String email;

    /**
     * Address of the emergency contact.
     */
    private String address;

    /**
     * Priority of the emergency contact.
     */
    private Integer priority;

    /**
     * Indicates whether the contact is active.
     */
    private boolean active;

    /**
     * Creation timestamp.
     */
    private LocalDateTime createdAt;

    /**
     * Last update timestamp.
     */
    private LocalDateTime updatedAt;
}
