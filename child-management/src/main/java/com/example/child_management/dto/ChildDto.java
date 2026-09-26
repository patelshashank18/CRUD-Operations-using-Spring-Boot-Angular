package com.example.child_management.dto;

import java.time.LocalDate;

import com.example.child_management.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChildDto {

    /**
     * Child ID.
     *
     * This is optional while creating a new child.
     */
    private Long id;

    /**
     * First name is mandatory.
     */
    @NotBlank(message = "First name is required")
    private String firstName;

    /**
     * Last name is mandatory.
     */
    @NotBlank(message = "Last name is required")
    private String lastName;

    /**
     * Date of birth is mandatory.
     *
     * @Past ensures the date is before today.
     */
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    /**
     * Gender is mandatory.
     */
    @NotNull(message = "Gender is required")
    private Gender gender;

    /**
     * Blood group.
     */
    @Size(max = 5, message = "Blood group is too long")
    private String bloodGroup;

    /**
     * Child status.
     */
    @NotBlank(message = "Status is required")
    private String status;

    /**
     * Parent name is mandatory.
     */
    @NotBlank(message = "Parent name is required")
    private String parentName;

    /**
     * Mobile number is mandatory.
     */
    @NotBlank(message = "Mobile number is required")
    @Size(min = 10, max = 15, message = "Mobile number must contain 10 to 15 characters")
    private String mobile;

    /**
     * Email must have a valid email format.
     */
    @Email(message = "Invalid email address")
    private String email;

    /**
     * Child address.
     */
    private String address;
}