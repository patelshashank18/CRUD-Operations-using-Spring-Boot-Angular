package com.example.child_management.guardian.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a Guardian in ChildCare360.
 *
 * This class is connected to the guardian
 * table in the ChildCare360 database.
 */
@Entity
@Table(name = "guardian")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Guardian {

    /**
     * Unique ID of the guardian.
     *
     * The database generates this value automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    /**
     * Guardian's first name.
     */
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    /**
     * Guardian's last name.
     */
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    /**
     * Relationship between guardian and child.
     *
     * Example:
     * Father
     * Mother
     * Grandfather
     * Legal Guardian
     */
    @Column(name = "relationship", nullable = false, length = 50)
    private String relationship;

    /**
     * Guardian's mobile number.
     *
     * Each guardian must have a unique mobile number.
     */
    @Column(name = "mobile", nullable = false, unique = true, length = 20)
    private String mobile;

    /**
     * Guardian's email address.
     */
    @Column(name = "email", length = 150)
    private String email;

    /**
     * Guardian's residential address.
     */
    @Column(name = "address", length = 500)
    private String address;

    /**
     * Indicates whether the guardian is active.
     *
     * true = active
     * false = inactive
     */
    @Column(name = "active", nullable = false)
    private boolean active = true;

    /**
     * Date and time when the guardian was created.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Date and time when the guardian was last updated.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Automatically runs before inserting
     * a new guardian into the database.
     */
    @PrePersist
    protected void onCreate() {

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    /**
     * Automatically runs before updating
     * an existing guardian.
     */
    @PreUpdate
    protected void onUpdate() {

        updatedAt = LocalDateTime.now();
    }
}
