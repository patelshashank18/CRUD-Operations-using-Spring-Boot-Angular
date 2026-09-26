package com.example.child_management.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO used to send emergency contact statistics
 * to the ChildCare360 dashboard.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContactStatisticsDto {

    /**
     * Total number of emergency contacts.
     */
    private long totalEmergencyContacts;

    /**
     * Number of active emergency contacts.
     */
    private long activeEmergencyContacts;

    /**
     * Number of inactive emergency contacts.
     */
    private long inactiveEmergencyContacts;
}