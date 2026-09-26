package com.example.child_management.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO used to send the complete dashboard summary
 * to the ChildCare360 client.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummaryDto {

    /**
     * Child statistics.
     */
    private ChildStatisticsDto children;

    /**
     * Guardian statistics.
     */
    private GuardianStatisticsDto guardians;

    /**
     * Emergency contact statistics.
     */
    private EmergencyContactStatisticsDto emergencyContacts;
}