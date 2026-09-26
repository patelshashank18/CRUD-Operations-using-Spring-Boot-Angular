package com.example.child_management.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO used to send guardian statistics
 * to the ChildCare360 dashboard.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuardianStatisticsDto {

    /**
     * Total number of guardians.
     */
    private long totalGuardians;

    /**
     * Number of active guardians.
     */
    private long activeGuardians;

    /**
     * Number of inactive guardians.
     */
    private long inactiveGuardians;
}
