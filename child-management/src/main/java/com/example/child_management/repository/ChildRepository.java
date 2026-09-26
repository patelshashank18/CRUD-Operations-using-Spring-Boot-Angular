package com.example.child_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.child_management.entity.Child;
import com.example.child_management.enums.Gender;

/**
 * Repository for Child entity.
 *
 * Provides CRUD operations and dynamic
 * filtering support.
 */
public interface ChildRepository
                extends JpaRepository<Child, Long>,
                JpaSpecificationExecutor<Child> {

        /**
         * Searches children by first name
         * or last name.
         *
         * @param firstName first name to search
         * @param lastName  last name to search
         * @return matching children
         */
        List<Child> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        String firstName,
                        String lastName);

        /**
         * Finds children by gender.
         *
         * @param gender child gender
         * @return children with the given gender
         */
        List<Child> findByGender(
                        Gender gender);

        /**
         * Finds children by status.
         *
         * @param status child status
         * @return children with the given status
         */
        List<Child> findByStatus(
                        String status);

        /**
         * Counts children by status.
         *
         * @param status child status
         * @return number of children with the given status
         */
        long countByStatus(
                        String status);

        /**
         * Finds children by blood group.
         *
         * @param bloodGroup child blood group
         * @return children with the given blood group
         */
        List<Child> findByBloodGroup(
                        String bloodGroup);
}
