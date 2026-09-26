package com.example.child_management.entity;

import java.time.LocalDate;

import com.example.child_management.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "children")
@Getter
@Setter
public class Child {

    /**
     * Primary key of the child.
     *
     * The database automatically generates
     * the ID when a new child is inserted.
     *
     * Example:
     * 1, 2, 3, 4...
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Child's first name.
     *
     * Example:
     * Rahul
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Child's last name.
     *
     * Example:
     * Patel
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Child's date of birth.
     *
     * Example:
     * 2018-05-10
     */
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    /**
     * Child's gender.
     *
     * EnumType.STRING stores:
     *
     * MALE
     * FEMALE
     * OTHER
     *
     * instead of storing the enum number.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    /**
     * Blood group of the child.
     *
     * Example:
     * O+
     * A+
     * B+
     */
    @Column(name = "blood_group")
    private String bloodGroup;

    /**
     * Current status of the child.
     *
     * Example:
     * ACTIVE
     * INACTIVE
     */
    @Column(nullable = false)
    private String status;

    /**
     * Parent or guardian name.
     *
     * Example:
     * Jigar Patel
     */
    @Column(name = "parent_name", nullable = false)
    private String parentName;

    /**
     * Parent or guardian mobile number.
     */
    @Column(nullable = false, unique = true)
    private String mobile;

    /**
     * Parent or guardian email address.
     */
    private String email;

    /**
     * Residential address of the child.
     */
    private String address;
}