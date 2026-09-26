
package com.example.child_management.relationship.service;

import java.util.List;

import com.example.child_management.relationship.dto.ChildGuardianResponseDto;

/**
 * Service interface for managing
 * Child and Guardian relationships.
 */
public interface ChildGuardianService {

    /**
     * Connects a guardian to a child.
     *
     * @param childId    ID of the child
     * @param guardianId ID of the guardian
     */
    void addGuardianToChild(
            Long childId,
            Long guardianId);

    /**
     * Gets all guardians connected to a child.
     *
     * @param childId ID of the child
     * @return list of guardian response DTOs
     */
    List<ChildGuardianResponseDto> getGuardiansByChildId(Long childId);

    /**
     * Removes a guardian from a child.
     *
     * @param childId    ID of the child
     * @param guardianId ID of the guardian
     */
    void removeGuardianFromChild(
            Long childId,
            Long guardianId);
}
