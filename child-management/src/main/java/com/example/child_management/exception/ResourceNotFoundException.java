
package com.example.child_management.exception;

/**
 * Custom exception used when a requested resource
 * cannot be found in the database.
 *
 * Example:
 * If the user requests child ID 999 and that child
 * does not exist, this exception will be thrown.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Creates a new ResourceNotFoundException.
     *
     * @param message explanation of the error
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
