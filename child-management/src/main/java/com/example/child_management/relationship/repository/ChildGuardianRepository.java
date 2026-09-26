
package com.example.child_management.relationship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.child_management.relationship.entity.ChildGuardian;
import com.example.child_management.relationship.entity.ChildGuardianId;

/**
 * Repository for ChildGuardian relationships.
 *
 * This repository communicates with the
 * child_guardian database table.
 */
public interface ChildGuardianRepository
        extends JpaRepository<ChildGuardian, ChildGuardianId> {

    /**
     * Finds all relationships for a specific child.
     *
     * child -> id
     *
     * @param childId ID of the child
     * @return child-guardian relationships
     */
    List<ChildGuardian> findByChild_Id(Long childId);

    /**
     * Finds all relationships for a specific guardian.
     *
     * guardian -> id
     *
     * @param guardianId ID of the guardian
     * @return child-guardian relationships
     */
    List<ChildGuardian> findByGuardian_Id(Long guardianId);

    /**
     * Checks whether a child and guardian
     * are already connected.
     *
     * child -> id
     * guardian -> id
     *
     * @param childId    ID of the child
     * @param guardianId ID of the guardian
     * @return true if relationship exists
     */
    boolean existsByChild_IdAndGuardian_Id(
            Long childId,
            Long guardianId);
}
