package com.example.child_management.relationship.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.child_management.entity.Child;
import com.example.child_management.exception.ResourceNotFoundException;
import com.example.child_management.guardian.entity.Guardian;
import com.example.child_management.guardian.repository.GuardianRepository;
import com.example.child_management.relationship.dto.ChildGuardianResponseDto;
import com.example.child_management.relationship.entity.ChildGuardian;
import com.example.child_management.relationship.entity.ChildGuardianId;
import com.example.child_management.relationship.repository.ChildGuardianRepository;
import com.example.child_management.relationship.service.ChildGuardianService;
import com.example.child_management.repository.ChildRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of ChildGuardianService.
 *
 * This class contains the business logic for connecting
 * children with their guardians.
 *
 * Responsibilities:
 *
 * 1. Connect a guardian to a child.
 * 2. Get all guardians of a child.
 * 3. Remove a guardian from a child.
 * 4. Convert Guardian entity into a response DTO.
 */
@Service
@RequiredArgsConstructor
public class ChildGuardianServiceImpl
                implements ChildGuardianService {

        /**
         * Repository used to find and manage children.
         */
        private final ChildRepository childRepository;

        /**
         * Repository used to find and manage guardians.
         */
        private final GuardianRepository guardianRepository;

        /**
         * Repository used to manage the child_guardian
         * relationship table.
         */
        private final ChildGuardianRepository childGuardianRepository;

        /**
         * Connects a guardian to a child.
         *
         * Example:
         *
         * Child ID = 10
         * Guardian ID = 1
         *
         * This creates a record in the child_guardian table.
         */
        @Override
        @Transactional
        public void addGuardianToChild(
                        Long childId,
                        Long guardianId) {

                /**
                 * First check whether the child exists.
                 *
                 * If the child does not exist, throw a
                 * ResourceNotFoundException.
                 */
                Child child = childRepository.findById(childId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Child not found with ID: "
                                                                + childId));

                /**
                 * Check whether the guardian exists.
                 *
                 * If the guardian does not exist, throw a
                 * ResourceNotFoundException.
                 */
                Guardian guardian = guardianRepository.findById(guardianId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Guardian not found with ID: "
                                                                + guardianId));

                /**
                 * Check whether this child and guardian
                 * relationship already exists.
                 *
                 * This prevents duplicate relationships.
                 */
                boolean relationshipExists = childGuardianRepository
                                .existsByChild_IdAndGuardian_Id(
                                                childId,
                                                guardianId);

                /**
                 * If the relationship already exists,
                 * return a clear error message.
                 */
                if (relationshipExists) {

                        throw new IllegalArgumentException(
                                        "Guardian is already connected to this child");
                }

                /**
                 * Create a new relationship object.
                 */
                ChildGuardian childGuardian = new ChildGuardian();

                /**
                 * Set the child.
                 */
                childGuardian.setChild(child);

                /**
                 * Set the guardian.
                 */
                childGuardian.setGuardian(guardian);

                /**
                 * Save the relationship into
                 * the child_guardian table.
                 */
                childGuardianRepository.save(childGuardian);
        }

        /**
         * Gets all guardians connected to a child.
         *
         * Example:
         *
         * GET /api/children/10/guardians
         *
         * The method returns DTOs instead of returning
         * the Guardian JPA entity directly.
         */
        @Override
        @Transactional(readOnly = true)
        public List<ChildGuardianResponseDto> getGuardiansByChildId(Long childId) {

                /**
                 * Check whether the child exists.
                 *
                 * This prevents returning an empty list when
                 * the requested child does not exist.
                 */
                childRepository.findById(childId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Child not found with ID: "
                                                                + childId));

                /**
                 * Find all relationship records for
                 * the specified child.
                 *
                 * Each record contains:
                 *
                 * child
                 * guardian
                 */
                List<ChildGuardian> relationships = childGuardianRepository
                                .findByChild_Id(childId);

                /**
                 * Convert every ChildGuardian relationship
                 * into ChildGuardianResponseDto.
                 *
                 * Stream is used to process the list.
                 */
                return relationships.stream()
                                .map(this::convertToResponse)
                                .toList();
        }

        /**
         * Removes a guardian from a child.
         *
         * Example:
         *
         * DELETE /api/children/10/guardians/1
         */
        @Override
        @Transactional
        public void removeGuardianFromChild(
                        Long childId,
                        Long guardianId) {

                /**
                 * Create the composite ID.
                 *
                 * The child_guardian table uses:
                 *
                 * child_id + guardian_id
                 *
                 * as the primary key.
                 */
                ChildGuardianId relationshipId = new ChildGuardianId(
                                childId,
                                guardianId);

                /**
                 * Check whether the relationship exists.
                 */
                if (!childGuardianRepository
                                .existsById(relationshipId)) {

                        /**
                         * If the relationship does not exist,
                         * return a standard 404 error.
                         */
                        throw new ResourceNotFoundException(
                                        "Child and Guardian relationship not found");
                }

                /**
                 * Delete the relationship.
                 *
                 * This removes the record from:
                 *
                 * child_guardian
                 */
                childGuardianRepository.deleteById(
                                relationshipId);
        }

        /**
         * Converts a ChildGuardian relationship into
         * a ChildGuardianResponseDto.
         *
         * We return only the guardian information required
         * by the REST API.
         *
         * This prevents the API from directly exposing
         * the JPA Guardian entity.
         */
        private ChildGuardianResponseDto convertToResponse(
                        ChildGuardian relationship) {

                /**
                 * Get the Guardian entity from the relationship.
                 */
                Guardian guardian = relationship.getGuardian();

                /**
                 * Create and return the response DTO.
                 */
                return new ChildGuardianResponseDto(
                                guardian.getId(),
                                guardian.getFirstName(),
                                guardian.getLastName(),
                                guardian.getRelationship(),
                                guardian.getMobile(),
                                guardian.getEmail(),
                                guardian.isActive());
        }
}
