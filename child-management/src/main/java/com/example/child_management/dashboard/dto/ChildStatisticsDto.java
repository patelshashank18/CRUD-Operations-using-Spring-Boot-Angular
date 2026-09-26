package com.example.child_management.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO used to send child statistics
 * to the ChildCare360 dashboard.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChildStatisticsDto {

    /**
     * Total number of children.
     */
    private long totalChildren;

    /**
     * Number of active children.
     */
    private long activeChildren;

    /**
     * Number of inactive children.
     */
    private long inactiveChildren;

    /**
     * Number of male children.
     */
    private long maleChildren;

    /**
     * Number of female children.
     */
    private long femaleChildren;
}
