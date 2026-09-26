package com.example.child_management.dashboard.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.child_management.dashboard.dto.ChildStatisticsDto;
import com.example.child_management.dashboard.dto.DashboardSummaryDto;
import com.example.child_management.dashboard.dto.EmergencyContactStatisticsDto;
import com.example.child_management.dashboard.dto.GuardianStatisticsDto;
import com.example.child_management.dashboard.service.DashboardService;
import com.example.child_management.response.ApiResponse;

import lombok.RequiredArgsConstructor;

/**
 * REST Controller for ChildCare360 dashboard APIs.
 *
 * Provides endpoints used by the dashboard
 * to display statistics.
 */
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

    private final DashboardService dashboardService;

    /**
     * Gets child statistics.
     *
     * @return child statistics
     */
    @GetMapping("/child-statistics")
    public ResponseEntity<ApiResponse<ChildStatisticsDto>> getChildStatistics() {

        ChildStatisticsDto statistics = dashboardService.getChildStatistics();

        ApiResponse<ChildStatisticsDto> response = new ApiResponse<>(
                true,
                "Child statistics retrieved successfully",
                statistics,
                LocalDateTime.now());

        return ResponseEntity.ok(response);
    }

    /**
     * Gets guardian statistics.
     *
     * @return guardian statistics
     */
    @GetMapping("/guardian-statistics")
    public ResponseEntity<ApiResponse<GuardianStatisticsDto>> getGuardianStatistics() {

        GuardianStatisticsDto statistics = dashboardService.getGuardianStatistics();

        ApiResponse<GuardianStatisticsDto> response = new ApiResponse<>(
                true,
                "Guardian statistics retrieved successfully",
                statistics,
                LocalDateTime.now());

        return ResponseEntity.ok(response);
    }

    /**
     * Gets emergency contact statistics.
     *
     * @return emergency contact statistics
     */
    @GetMapping("/emergency-contact-statistics")
    public ResponseEntity<ApiResponse<EmergencyContactStatisticsDto>> getEmergencyContactStatistics() {

        EmergencyContactStatisticsDto statistics = dashboardService.getEmergencyContactStatistics();

        ApiResponse<EmergencyContactStatisticsDto> response = new ApiResponse<>(
                true,
                "Emergency contact statistics retrieved successfully",
                statistics,
                LocalDateTime.now());

        return ResponseEntity.ok(response);
    }

    /**
     * Gets the complete dashboard summary.
     *
     * @return complete dashboard summary
     */
    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<DashboardSummaryDto>> getDashboardSummary() {

        DashboardSummaryDto summary = dashboardService.getDashboardSummary();

        ApiResponse<DashboardSummaryDto> response = new ApiResponse<>(
                true,
                "Dashboard summary retrieved successfully",
                summary,
                LocalDateTime.now());

        return ResponseEntity.ok(response);
    }
}
