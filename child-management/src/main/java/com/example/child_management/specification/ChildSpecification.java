package com.example.child_management.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.child_management.entity.Child;
import com.example.child_management.enums.Gender;

import jakarta.persistence.criteria.Predicate;

/**
 * Creates dynamic database filters for ChildCare360.
 *
 * This class allows multiple optional filters
 * to be combined into one database query.
 */
public class ChildSpecification {

        /**
         * Creates a dynamic specification for children.
         *
         * Supported filters:
         *
         * - Name
         * - Gender
         * - Status
         * - Blood group
         *
         * @param name       name search
         * @param gender     gender filter
         * @param status     status filter
         * @param bloodGroup blood group filter
         * @return dynamic database specification
         */
        public static Specification<Child> filterChildren(
                        String name,
                        Gender gender,
                        String status,
                        String bloodGroup) {

                return (root, query, criteriaBuilder) -> {

                        /**
                         * Stores all filter conditions.
                         */
                        List<Predicate> predicates = new ArrayList<>();

                        /**
                         * Search by first name OR last name.
                         *
                         * Example:
                         *
                         * name=rah
                         *
                         * Matches:
                         *
                         * Rahul
                         * Rahul Patel
                         */
                        if (name != null && !name.isBlank()) {

                                String searchValue = "%" +
                                                name.trim().toLowerCase() +
                                                "%";

                                Predicate firstNamePredicate = criteriaBuilder.like(
                                                criteriaBuilder.lower(
                                                                root.get("firstName")),
                                                searchValue);

                                Predicate lastNamePredicate = criteriaBuilder.like(
                                                criteriaBuilder.lower(
                                                                root.get("lastName")),
                                                searchValue);

                                predicates.add(
                                                criteriaBuilder.or(
                                                                firstNamePredicate,
                                                                lastNamePredicate));
                        }

                        /**
                         * Filter by gender.
                         */
                        if (gender != null) {

                                predicates.add(
                                                criteriaBuilder.equal(
                                                                root.get("gender"),
                                                                gender));
                        }

                        /**
                         * Filter by status.
                         */
                        if (status != null &&
                                        !status.isBlank()) {

                                predicates.add(
                                                criteriaBuilder.equal(
                                                                root.get("status"),
                                                                status));
                        }

                        /**
                         * Filter by blood group.
                         */
                        if (bloodGroup != null &&
                                        !bloodGroup.isBlank()) {

                                predicates.add(
                                                criteriaBuilder.equal(
                                                                root.get("bloodGroup"),
                                                                bloodGroup));
                        }

                        /**
                         * Combine all filters using AND.
                         *
                         * If no filters are supplied,
                         * an empty condition is returned,
                         * which means all children are returned.
                         */
                        return criteriaBuilder.and(
                                        predicates.toArray(
                                                        new Predicate[0]));
                };
        }
}