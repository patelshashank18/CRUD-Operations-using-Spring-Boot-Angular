package com.example.child_management.dashboard.service;

import com.example.child_management.dashboard.dto.ChildStatisticsDto;
import com.example.child_management.dashboard.dto.DashboardSummaryDto;
import com.example.child_management.dashboard.dto.EmergencyContactStatisticsDto;
import com.example.child_management.dashboard.dto.GuardianStatisticsDto;

/**
 * Service interface for ChildCare360 dashboard operations.
 *
 * Defines the business operations required to
 * generate dashboard statistics.
 */
public interface DashboardService {
    /**
     * Gets the complete dashboard summary.
     *
     * @return dashboard summary
     */
    DashboardSummaryDto getDashboardSummary();

    /**
     * Gets statistics about children.
     *
     * @return child statistics
     */
    ChildStatisticsDto getChildStatistics();

    /**
     * Gets statistics about guardians.
     *
     * @return guardian statistics
     */
    GuardianStatisticsDto getGuardianStatistics();

    /**
     * Gets statistics about emergency contacts.
     *
     * @return emergency contact statistics
     */
    EmergencyContactStatisticsDto getEmergencyContactStatistics();
}