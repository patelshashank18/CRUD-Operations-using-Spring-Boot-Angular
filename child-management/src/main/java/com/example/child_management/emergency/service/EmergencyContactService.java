package com.example.child_management.emergency.service;

import java.util.List;

import com.example.child_management.emergency.dto.EmergencyContactDto;
import com.example.child_management.emergency.dto.EmergencyContactResponseDto;

/**
 * Service interface for emergency contact management.
 */
public interface EmergencyContactService {

    /**
     * Creates an emergency contact.
     *
     * @param childId child ID
     * @param dto     emergency contact input
     * @return created emergency contact response
     */
    EmergencyContactResponseDto createContact(
            Long childId,
            EmergencyContactDto dto);

    /**
     * Gets all emergency contacts for a child.
     *
     * @param childId child ID
     * @return list of emergency contact responses
     */
    List<EmergencyContactResponseDto> getContactsByChildId(
            Long childId);

    /**
     * Gets one emergency contact by ID.
     *
     * @param id emergency contact ID
     * @return emergency contact response
     */
    EmergencyContactResponseDto getContactById(Long id);

    /**
     * Updates an emergency contact.
     *
     * @param id  emergency contact ID
     * @param dto updated emergency contact input
     * @return updated emergency contact response
     */
    EmergencyContactResponseDto updateContact(
            Long id,
            EmergencyContactDto dto);

    /**
     * Deletes an emergency contact.
     *
     * @param id emergency contact ID
     */
    void deleteContact(Long id);
}
