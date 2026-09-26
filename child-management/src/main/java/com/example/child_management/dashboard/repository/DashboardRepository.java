package com.example.child_management.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.child_management.entity.Child;

/**
 * Repository used to retrieve statistics
 * required by the ChildCare360 dashboard.
 *
 * This repository uses JPQL queries to count
 * children, guardians, and emergency contacts.
 */
public interface DashboardRepository extends JpaRepository<Child, Long> {

        /**
         * Counts all children.
         *
         * @return total number of children
         */
        @Query("SELECT COUNT(c) FROM Child c")
        long countTotalChildren();

        /**
         * Counts active children.
         *
         * @return number of active children
         */
        @Query("""
                        SELECT COUNT(c)
                        FROM Child c
                        WHERE UPPER(c.status) = 'ACTIVE'
                        """)
        long countActiveChildren();

        /**
         * Counts inactive children.
         *
         * @return number of inactive children
         */
        @Query("""
                        SELECT COUNT(c)
                        FROM Child c
                        WHERE UPPER(c.status) = 'INACTIVE'
                        """)
        long countInactiveChildren();

        /**
         * Counts male children.
         *
         * @return number of male children
         */
        @Query("""
                        SELECT COUNT(c)
                        FROM Child c
                        WHERE c.gender = com.example.child_management.enums.Gender.MALE
                        """)
        long countMaleChildren();

        /**
         * Counts female children.
         *
         * @return number of female children
         */
        @Query("""
                        SELECT COUNT(c)
                        FROM Child c
                        WHERE c.gender = com.example.child_management.enums.Gender.FEMALE
                        """)
        long countFemaleChildren();

        /**
         * Counts all guardians.
         *
         * @return total number of guardians
         */
        @Query("SELECT COUNT(g) FROM Guardian g")
        long countTotalGuardians();

        /**
         * Counts active guardians.
         *
         * @return number of active guardians
         */
        @Query("""
                        SELECT COUNT(g)
                        FROM Guardian g
                        WHERE g.active = true
                        """)
        long countActiveGuardians();

        /**
         * Counts inactive guardians.
         *
         * @return number of inactive guardians
         */
        @Query("""
                        SELECT COUNT(g)
                        FROM Guardian g
                        WHERE g.active = false
                        """)
        long countInactiveGuardians();

        /**
         * Counts all emergency contacts.
         *
         * @return total number of emergency contacts
         */
        @Query("SELECT COUNT(e) FROM EmergencyContact e")
        long countTotalEmergencyContacts();

        /**
         * Counts active emergency contacts.
         *
         * @return number of active emergency contacts
         */
        @Query("""
                        SELECT COUNT(e)
                        FROM EmergencyContact e
                        WHERE e.active = true
                        """)
        long countActiveEmergencyContacts();

        /**
         * Counts inactive emergency contacts.
         *
         * @return number of inactive emergency contacts
         */
        @Query("""
                        SELECT COUNT(e)
                        FROM EmergencyContact e
                        WHERE e.active = false
                        """)
        long countInactiveEmergencyContacts();
}
