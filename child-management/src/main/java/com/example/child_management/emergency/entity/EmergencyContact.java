package com.example.child_management.emergency.entity;

import java.time.LocalDateTime;

import com.example.child_management.entity.Child;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an emergency contact for a child.
 *
 * Each emergency contact belongs to one child.
 */
@Entity
@Table(name = "emergency_contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContact {

    /**
     * Unique ID of the emergency contact.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Child associated with this emergency contact.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    /**
     * Name of the emergency contact.
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * Relationship with the child.
     */
    @Column(name = "relationship", nullable = false, length = 50)
    private String relationship;

    /**
     * Primary mobile number.
     */
    @Column(name = "mobile", nullable = false, length = 20)
    private String mobile;

    /**
     * Alternative mobile number.
     */
    @Column(name = "alternate_mobile", length = 20)
    private String alternateMobile;

    /**
     * Email address.
     */
    @Column(name = "email", length = 150)
    private String email;

    /**
     * Residential address.
     */
    @Column(name = "address", length = 500)
    private String address;

    /**
     * Priority of this emergency contact.
     *
     * 1 means highest priority.
     */
    @Column(name = "priority", nullable = false)
    private Integer priority = 1;

    /**
     * Indicates whether the contact is active.
     */
    @Column(name = "active", nullable = false)
    private boolean active = true;

    /**
     * Creation timestamp.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Last update timestamp.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Sets timestamps before inserting.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    /**
     * Updates the timestamp before updating.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}