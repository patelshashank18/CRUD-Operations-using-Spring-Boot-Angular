package com.example.child_management.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.child_management.dto.ChildDto;
import com.example.child_management.enums.Gender;

/**
 * Service interface for ChildCare360.
 *
 * Defines all child-related business operations.
 */
public interface ChildService {

        /**
         * Creates a child.
         */
        ChildDto createChild(
                        ChildDto childDto);

        /**
         * Gets all children.
         */
        List<ChildDto> getAllChildren();

        /**
         * Gets a child by ID.
         */
        ChildDto getChildById(
                        Long id);

        /**
         * Updates a child.
         */
        ChildDto updateChild(
                        Long id,
                        ChildDto childDto);

        /**
         * Deletes a child.
         */
        void deleteChild(
                        Long id);

        /**
         * Searches children by name.
         */
        List<ChildDto> searchChildren(
                        String name);

        /**
         * Gets children with pagination
         * and sorting.
         */
        Page<ChildDto> getChildrenWithPagination(
                        int page,
                        int size,
                        String sortBy,
                        String direction);

        /**
         * Filters children by gender.
         */
        List<ChildDto> filterByGender(
                        Gender gender);

        /**
         * Filters children by status.
         */
        List<ChildDto> filterByStatus(
                        String status);

        /**
         * Filters children by blood group.
         */
        List<ChildDto> filterByBloodGroup(
                        String bloodGroup);

        /**
         * Performs combined search,
         * filtering, pagination and sorting.
         */
        Page<ChildDto> getChildren(
                        String name,
                        Gender gender,
                        String status,
                        String bloodGroup,
                        int page,
                        int size,
                        String sortBy,
                        String direction);
}
