package com.example.child_management.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.child_management.dto.ChildDto;
import com.example.child_management.enums.Gender;
import com.example.child_management.response.ApiResponse;
import com.example.child_management.response.PaginationResponse;
import com.example.child_management.service.ChildService;

import jakarta.validation.Valid;

/**
 * REST Controller for ChildCare360.
 *
 * This controller handles HTTP requests related
 * to child management.
 *
 * Base URL:
 * /api/children
 *
 * Supported operations:
 * - Create child
 * - Get children
 * - Get child by ID
 * - Update child
 * - Delete child
 * - Search children
 * - Filter children
 * - Pagination
 * - Sorting
 */
@RestController
@RequestMapping("/api/children")
@CrossOrigin(origins = "http://localhost:4200")
public class ChildController {

        /**
         * Service responsible for child business logic.
         */
        private final ChildService childService;

        /**
         * Constructor injection.
         *
         * Spring automatically provides the ChildService object.
         *
         * @param childService child business service
         */
        public ChildController(ChildService childService) {
                this.childService = childService;
        }

        /**
         * Creates a new child.
         *
         * HTTP:
         * POST /api/children
         *
         * @param childDto child information received from client
         * @return standard API response containing the created child
         */
        @PostMapping
        public ResponseEntity<ApiResponse<ChildDto>> createChild(
                        @Valid @RequestBody ChildDto childDto) {

                ChildDto savedChild = childService.createChild(childDto);

                ApiResponse<ChildDto> response = new ApiResponse<>(
                                true,
                                "Child created successfully",
                                savedChild,
                                LocalDateTime.now());

                return ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(response);
        }

        /**
         * Gets children using optional search and filter parameters.
         *
         * This is the main ChildCare360 listing API.
         *
         * Supported parameters:
         * - name
         * - gender
         * - status
         * - bloodGroup
         * - page
         * - size
         * - sortBy
         * - direction
         *
         * @return standard API response containing paginated children
         */
        @GetMapping
        public ResponseEntity<ApiResponse<PaginationResponse<List<ChildDto>>>> getAllChildren(

                        /**
                         * Searches first name or last name.
                         */
                        @RequestParam(required = false) String name,

                        /**
                         * Filters children by gender.
                         */
                        @RequestParam(required = false) Gender gender,

                        /**
                         * Filters children by status.
                         */
                        @RequestParam(required = false) String status,

                        /**
                         * Filters children by blood group.
                         */
                        @RequestParam(required = false) String bloodGroup,

                        /**
                         * Page number.
                         *
                         * Page numbering starts from 0.
                         */
                        @RequestParam(defaultValue = "0") int page,

                        /**
                         * Number of children per page.
                         */
                        @RequestParam(defaultValue = "10") int size,

                        /**
                         * Field used for sorting.
                         */
                        @RequestParam(defaultValue = "id") String sortBy,

                        /**
                         * Sorting direction.
                         *
                         * Allowed values:
                         * - asc
                         * - desc
                         */
                        @RequestParam(defaultValue = "asc") String direction) {

                Page<ChildDto> childPage = childService.getChildren(
                                name,
                                gender,
                                status,
                                bloodGroup,
                                page,
                                size,
                                sortBy,
                                direction);

                PaginationResponse<List<ChildDto>> paginationResponse = new PaginationResponse<>(
                                childPage.getContent(),
                                childPage.getNumber(),
                                childPage.getSize(),
                                childPage.getTotalElements(),
                                childPage.getTotalPages());

                ApiResponse<PaginationResponse<List<ChildDto>>> response = new ApiResponse<>(
                                true,
                                "Children retrieved successfully",
                                paginationResponse,
                                LocalDateTime.now());

                return ResponseEntity.ok(response);
        }

        /**
         * Searches children by first name or last name.
         *
         * HTTP:
         * GET /api/children/search?name=rah
         *
         * @param name name to search
         * @return matching children
         */
        @GetMapping("/search")
        public ResponseEntity<ApiResponse<List<ChildDto>>> searchChildren(
                        @RequestParam("name") String name) {

                List<ChildDto> children = childService.searchChildren(name);

                ApiResponse<List<ChildDto>> response = new ApiResponse<>(
                                true,
                                "Children found successfully",
                                children,
                                LocalDateTime.now());

                return ResponseEntity.ok(response);
        }

        /**
         * Filters children by gender.
         *
         * HTTP:
         * GET /api/children/filter/gender/MALE
         *
         * @param gender child gender
         * @return matching children
         */
        @GetMapping("/filter/gender/{gender}")
        public ResponseEntity<ApiResponse<List<ChildDto>>> filterByGender(
                        @PathVariable Gender gender) {

                List<ChildDto> children = childService.filterByGender(gender);

                ApiResponse<List<ChildDto>> response = new ApiResponse<>(
                                true,
                                "Children filtered by gender successfully",
                                children,
                                LocalDateTime.now());

                return ResponseEntity.ok(response);
        }

        /**
         * Filters children by status.
         *
         * HTTP:
         * GET /api/children/filter/status/ACTIVE
         *
         * @param status child status
         * @return matching children
         */
        @GetMapping("/filter/status/{status}")
        public ResponseEntity<ApiResponse<List<ChildDto>>> filterByStatus(
                        @PathVariable String status) {

                List<ChildDto> children = childService.filterByStatus(status);

                ApiResponse<List<ChildDto>> response = new ApiResponse<>(
                                true,
                                "Children filtered by status successfully",
                                children,
                                LocalDateTime.now());

                return ResponseEntity.ok(response);
        }

        /**
         * Filters children by blood group.
         *
         * HTTP:
         * GET /api/children/filter/blood-group/O%2B
         *
         * @param bloodGroup child blood group
         * @return matching children
         */
        @GetMapping("/filter/blood-group/{bloodGroup}")
        public ResponseEntity<ApiResponse<List<ChildDto>>> filterByBloodGroup(
                        @PathVariable String bloodGroup) {

                List<ChildDto> children = childService.filterByBloodGroup(bloodGroup);

                ApiResponse<List<ChildDto>> response = new ApiResponse<>(
                                true,
                                "Children filtered by blood group successfully",
                                children,
                                LocalDateTime.now());

                return ResponseEntity.ok(response);
        }

        /**
         * Gets a single child by ID.
         *
         * HTTP:
         * GET /api/children/2
         *
         * @param id child ID
         * @return child information
         */
        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<ChildDto>> getChildById(
                        @PathVariable Long id) {

                ChildDto child = childService.getChildById(id);

                ApiResponse<ChildDto> response = new ApiResponse<>(
                                true,
                                "Child retrieved successfully",
                                child,
                                LocalDateTime.now());

                return ResponseEntity.ok(response);
        }

        /**
         * Updates an existing child.
         *
         * HTTP:
         * PUT /api/children/2
         *
         * @param id       child ID
         * @param childDto updated child information
         * @return updated child
         */
        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<ChildDto>> updateChild(
                        @PathVariable Long id,
                        @Valid @RequestBody ChildDto childDto) {

                ChildDto updatedChild = childService.updateChild(
                                id,
                                childDto);

                ApiResponse<ChildDto> response = new ApiResponse<>(
                                true,
                                "Child updated successfully",
                                updatedChild,
                                LocalDateTime.now());

                return ResponseEntity.ok(response);
        }

        /**
         * Deletes a child.
         *
         * HTTP:
         * DELETE /api/children/2
         *
         * @param id child ID
         * @return standard API response confirming deletion
         */
        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> deleteChild(
                        @PathVariable Long id) {

                childService.deleteChild(id);

                ApiResponse<Void> response = new ApiResponse<>(
                                true,
                                "Child deleted successfully",
                                null,
                                LocalDateTime.now());

                return ResponseEntity.ok(response);
        }
}