package com.example.child_management.guardian.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.child_management.guardian.entity.Guardian;

/**
 * Repository for Guardian database operations.
 *
 * JpaRepository provides standard CRUD operations.
 */
public interface GuardianRepository
                extends JpaRepository<Guardian, Long> {

        /**
         * Counts active or inactive guardians.
         *
         * @param active active status
         * @return number of guardians with the given status
         */
        long countByActive(boolean active);
}
