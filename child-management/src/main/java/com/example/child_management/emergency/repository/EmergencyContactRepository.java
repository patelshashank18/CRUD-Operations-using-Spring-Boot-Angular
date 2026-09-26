package com.example.child_management.emergency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.child_management.emergency.entity.EmergencyContact;

/**
 * Repository for Emergency Contact database operations.
 */
public interface EmergencyContactRepository
        extends JpaRepository<EmergencyContact, Long> {

    /**
     * Finds all emergency contacts belonging to a child.
     */
    List<EmergencyContact> findByChildId(Long childId);

    /**
     * Checks whether the same mobile number
     * already exists for the same child.
     *
     * @param childId child ID
     * @param mobile  emergency contact mobile number
     * @return true when the mobile already exists for this child
     */
    boolean existsByChildIdAndMobile(
            Long childId,
            String mobile);

    /**
     * Counts active or inactive emergency contacts.
     *
     * @param active active status
     * @return number of matching contacts
     */
    long countByActive(boolean active);
}