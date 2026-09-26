
package com.example.child_management.relationship.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Composite primary key for ChildGuardian.
 *
 * The child_guardian table uses:
 *
 * child_id + guardian_id
 *
 * as its combined primary key.
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ChildGuardianId implements Serializable {

    /**
     * ID of the child.
     */
    private Long child;

    /**
     * ID of the guardian.
     */
    private Long guardian;
}
