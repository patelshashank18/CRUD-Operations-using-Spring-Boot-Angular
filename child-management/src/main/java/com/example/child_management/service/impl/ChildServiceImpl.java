package com.example.child_management.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.child_management.dto.ChildDto;
import com.example.child_management.entity.Child;
import com.example.child_management.enums.Gender;
import com.example.child_management.exception.ResourceNotFoundException;
import com.example.child_management.repository.ChildRepository;
import com.example.child_management.service.ChildService;
import com.example.child_management.specification.ChildSpecification;

/**
 * Service implementation for ChildCare360.
 *
 * Handles child CRUD operations, search, filtering,
 * pagination, sorting, and combined filtering.
 */
@Service
public class ChildServiceImpl implements ChildService {

        /**
         * Repository used to communicate with the database.
         */
        private final ChildRepository childRepository;

        /**
         * Fields allowed for sorting child records.
         */
        private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
                        "id",
                        "firstName",
                        "lastName",
                        "dateOfBirth",
                        "gender",
                        "bloodGroup",
                        "status",
                        "parentName",
                        "mobile",
                        "email");

        /**
         * Creates the service with the required repository.
         *
         * @param childRepository child repository
         */
        public ChildServiceImpl(ChildRepository childRepository) {
                this.childRepository = childRepository;
        }

        /**
         * Creates a new child.
         *
         * @param childDto child information
         * @return saved child information
         */
        @Override
        public ChildDto createChild(ChildDto childDto) {

                Child child = convertToEntity(childDto);

                Child savedChild = childRepository.save(child);

                return convertToDto(savedChild);
        }

        /**
         * Gets all children without pagination.
         *
         * @return list of all children
         */
        @Override
        public List<ChildDto> getAllChildren() {

                return childRepository.findAll()
                                .stream()
                                .map(this::convertToDto)
                                .toList();
        }

        /**
         * Gets one child by ID.
         *
         * @param id child ID
         * @return child information
         * @throws ResourceNotFoundException if child does not exist
         */
        @Override
        public ChildDto getChildById(Long id) {

                Child child = childRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Child not found with ID: " + id));

                return convertToDto(child);
        }

        /**
         * Updates an existing child.
         *
         * @param id       child ID
         * @param childDto updated child information
         * @return updated child information
         * @throws ResourceNotFoundException if child does not exist
         */
        @Override
        public ChildDto updateChild(Long id, ChildDto childDto) {

                Child child = childRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Child not found with ID: " + id));

                child.setFirstName(childDto.getFirstName());
                child.setLastName(childDto.getLastName());
                child.setDateOfBirth(childDto.getDateOfBirth());
                child.setGender(childDto.getGender());
                child.setBloodGroup(childDto.getBloodGroup());
                child.setStatus(childDto.getStatus());
                child.setParentName(childDto.getParentName());
                child.setMobile(childDto.getMobile());
                child.setEmail(childDto.getEmail());
                child.setAddress(childDto.getAddress());

                Child updatedChild = childRepository.save(child);

                return convertToDto(updatedChild);
        }

        /**
         * Deletes a child.
         *
         * @param id child ID
         * @throws ResourceNotFoundException if child does not exist
         */
        @Override
        public void deleteChild(Long id) {

                if (!childRepository.existsById(id)) {
                        throw new ResourceNotFoundException(
                                        "Child not found with ID: " + id);
                }

                childRepository.deleteById(id);
        }

        /**
         * Searches children by first name or last name.
         *
         * @param name name to search
         * @return matching children
         */
        @Override
        public List<ChildDto> searchChildren(String name) {

                List<Child> children = childRepository
                                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                                                name,
                                                name);

                return children.stream()
                                .map(this::convertToDto)
                                .toList();
        }

        /**
         * Gets children with pagination and sorting.
         *
         * @param page      page number
         * @param size      number of records per page
         * @param sortBy    sorting field
         * @param direction sorting direction
         * @return paginated children
         */
        @Override
        public Page<ChildDto> getChildrenWithPagination(
                        int page,
                        int size,
                        String sortBy,
                        String direction) {

                // Validate pagination values.
                validatePagination(page, size);

                // Validate sorting field.
                validateSortField(sortBy);

                // Convert text direction to Spring Sort.Direction.
                Sort.Direction sortDirection = getSortDirection(direction);

                // Create sorting configuration.
                Sort sort = Sort.by(sortDirection, sortBy);

                // Create pagination configuration.
                Pageable pageable = PageRequest.of(page, size, sort);

                // Fetch children from the database.
                Page<Child> childPage = childRepository.findAll(pageable);

                return childPage.map(this::convertToDto);
        }

        /**
         * Filters children by gender.
         *
         * @param gender gender filter
         * @return matching children
         */
        @Override
        public List<ChildDto> filterByGender(Gender gender) {

                return childRepository.findByGender(gender)
                                .stream()
                                .map(this::convertToDto)
                                .toList();
        }

        /**
         * Filters children by status.
         *
         * @param status status filter
         * @return matching children
         */
        @Override
        public List<ChildDto> filterByStatus(String status) {

                return childRepository.findByStatus(status)
                                .stream()
                                .map(this::convertToDto)
                                .toList();
        }

        /**
         * Filters children by blood group.
         *
         * @param bloodGroup blood group filter
         * @return matching children
         */
        @Override
        public List<ChildDto> filterByBloodGroup(String bloodGroup) {

                return childRepository.findByBloodGroup(bloodGroup)
                                .stream()
                                .map(this::convertToDto)
                                .toList();
        }

        /**
         * Gets children using combined search, filtering,
         * pagination, and sorting.
         *
         * @param name       name search
         * @param gender     gender filter
         * @param status     status filter
         * @param bloodGroup blood group filter
         * @param page       page number
         * @param size       number of records per page
         * @param sortBy     sorting field
         * @param direction  sorting direction
         * @return filtered and paginated children
         */
        @Override
        public Page<ChildDto> getChildren(
                        String name,
                        Gender gender,
                        String status,
                        String bloodGroup,
                        int page,
                        int size,
                        String sortBy,
                        String direction) {

                // Validate pagination values.
                validatePagination(page, size);

                // Validate sorting field.
                validateSortField(sortBy);

                // Convert text direction to Spring Sort.Direction.
                Sort.Direction sortDirection = getSortDirection(direction);

                // Create sorting configuration.
                Sort sort = Sort.by(sortDirection, sortBy);

                // Create pagination configuration.
                Pageable pageable = PageRequest.of(page, size, sort);

                // Build dynamic filtering conditions.
                Specification<Child> specification = ChildSpecification.filterChildren(
                                name,
                                gender,
                                status,
                                bloodGroup);

                // Execute the dynamic query.
                Page<Child> childPage = childRepository.findAll(
                                specification,
                                pageable);

                // Convert entities to DTOs.
                return childPage.map(this::convertToDto);
        }

        /**
         * Validates pagination values.
         *
         * Page number must be zero or greater.
         * Page size must be between 1 and 100.
         *
         * @param page page number
         * @param size page size
         */
        private void validatePagination(int page, int size) {

                if (page < 0) {
                        throw new IllegalArgumentException(
                                        "Page number cannot be negative.");
                }

                if (size < 1 || size > 100) {
                        throw new IllegalArgumentException(
                                        "Page size must be between 1 and 100.");
                }
        }

        /**
         * Validates the requested sorting field.
         *
         * @param sortBy requested sorting field
         */
        private void validateSortField(String sortBy) {

                if (sortBy == null ||
                                !ALLOWED_SORT_FIELDS.contains(sortBy)) {

                        throw new IllegalArgumentException(
                                        "Invalid sort field: " +
                                                        sortBy +
                                                        ". Allowed fields are: " +
                                                        ALLOWED_SORT_FIELDS);
                }
        }

        /**
         * Converts text direction into Spring Sort.Direction.
         *
         * @param direction sorting direction
         * @return ASC or DESC
         */
        private Sort.Direction getSortDirection(String direction) {

                if (direction == null || direction.isBlank()) {
                        return Sort.Direction.ASC;
                }

                if (direction.equalsIgnoreCase("asc")) {
                        return Sort.Direction.ASC;
                }

                if (direction.equalsIgnoreCase("desc")) {
                        return Sort.Direction.DESC;
                }

                throw new IllegalArgumentException(
                                "Invalid sort direction: " +
                                                direction +
                                                ". Allowed values are: asc, desc.");
        }

        /**
         * Converts Child entity to ChildDto.
         *
         * @param child child entity
         * @return child DTO
         */
        private ChildDto convertToDto(Child child) {

                ChildDto dto = new ChildDto();

                dto.setId(child.getId());
                dto.setFirstName(child.getFirstName());
                dto.setLastName(child.getLastName());
                dto.setDateOfBirth(child.getDateOfBirth());
                dto.setGender(child.getGender());
                dto.setBloodGroup(child.getBloodGroup());
                dto.setStatus(child.getStatus());
                dto.setParentName(child.getParentName());
                dto.setMobile(child.getMobile());
                dto.setEmail(child.getEmail());
                dto.setAddress(child.getAddress());

                return dto;
        }

        /**
         * Converts ChildDto to Child entity.
         *
         * @param dto child DTO
         * @return child entity
         */
        private Child convertToEntity(ChildDto dto) {

                Child child = new Child();

                child.setFirstName(dto.getFirstName());
                child.setLastName(dto.getLastName());
                child.setDateOfBirth(dto.getDateOfBirth());
                child.setGender(dto.getGender());
                child.setBloodGroup(dto.getBloodGroup());
                child.setStatus(dto.getStatus());
                child.setParentName(dto.getParentName());
                child.setMobile(dto.getMobile());
                child.setEmail(dto.getEmail());
                child.setAddress(dto.getAddress());

                return child;
        }
}
