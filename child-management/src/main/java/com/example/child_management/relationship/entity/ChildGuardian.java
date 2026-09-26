package com.example.child_management.relationship.entity;

import com.example.child_management.guardian.entity.Guardian;
import com.example.child_management.entity.Child;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the relationship between a Child and a Guardian.
 *
 * This entity maps to the child_guardian table.
 *
 * A child can have multiple guardians.
 * A guardian can be connected to multiple children.
 *
 * Therefore, child_guardian acts as a bridge table.
 */
@Entity
@Table(name = "child_guardian")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ChildGuardianId.class)
public class ChildGuardian {

    /**
     * ID of the child.
     *
     * This is one part of the composite primary key.
     */
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    /**
     * ID of the guardian.
     *
     * This is the second part of the composite primary key.
     */
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guardian_id", nullable = false)
    private Guardian guardian;
}
