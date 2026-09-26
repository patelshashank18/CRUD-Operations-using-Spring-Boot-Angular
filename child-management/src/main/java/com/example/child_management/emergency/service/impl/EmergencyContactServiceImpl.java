package com.example.child_management.emergency.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.child_management.emergency.dto.EmergencyContactDto;
import com.example.child_management.emergency.dto.EmergencyContactResponseDto;
import com.example.child_management.emergency.entity.EmergencyContact;
import com.example.child_management.emergency.repository.EmergencyContactRepository;
import com.example.child_management.emergency.service.EmergencyContactService;
import com.example.child_management.entity.Child;
import com.example.child_management.exception.ResourceNotFoundException;
import com.example.child_management.repository.ChildRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of EmergencyContactService.
 *
 * Contains business logic for emergency contacts.
 */
@Service
@RequiredArgsConstructor
public class EmergencyContactServiceImpl
        implements EmergencyContactService {

    private final ChildRepository childRepository;

    private final EmergencyContactRepository emergencyContactRepository;

    /**
     * Creates an emergency contact.
     *
     * Prevents the same mobile number from being
     * registered twice for the same child.
     */
    @Override
    @Transactional
    public EmergencyContactResponseDto createContact(
            Long childId,
            EmergencyContactDto dto) {

        /**
         * Finds the child before creating the contact.
         */
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Child not found with ID: " + childId));

        /**
         * Checks whether this mobile number already
         * exists for the same child.
         */
        if (emergencyContactRepository
                .existsByChildIdAndMobile(childId, dto.getMobile())) {

            throw new IllegalArgumentException(
                    "Emergency contact mobile already exists for this child");
        }

        EmergencyContact contact = new EmergencyContact();

        contact.setChild(child);
        contact.setName(dto.getName());
        contact.setRelationship(dto.getRelationship());
        contact.setMobile(dto.getMobile());
        contact.setAlternateMobile(dto.getAlternateMobile());
        contact.setEmail(dto.getEmail());
        contact.setAddress(dto.getAddress());
        contact.setPriority(dto.getPriority());
        contact.setActive(dto.isActive());

        EmergencyContact savedContact = emergencyContactRepository.save(contact);

        return convertToResponse(savedContact);
    }

    /**
     * Gets all emergency contacts for a child.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmergencyContactResponseDto> getContactsByChildId(
            Long childId) {

        /**
         * Verifies that the child exists.
         */
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Child not found with ID: " + childId));

        /**
         * Gets all contacts belonging to the child.
         *
         * Uses findByChildId because this is the repository
         * method currently defined in EmergencyContactRepository.
         */
        return emergencyContactRepository
                .findByChildId(childId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    /**
     * Gets one emergency contact by ID.
     */
    @Override
    @Transactional(readOnly = true)
    public EmergencyContactResponseDto getContactById(Long id) {

        EmergencyContact contact = emergencyContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Emergency contact not found with ID: " + id));

        return convertToResponse(contact);
    }

    /**
     * Updates an emergency contact.
     *
     * Prevents the same mobile number from being
     * used by another emergency contact for the same child.
     */
    @Override
    @Transactional
    public EmergencyContactResponseDto updateContact(
            Long id,
            EmergencyContactDto dto) {

        EmergencyContact contact = emergencyContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Emergency contact not found with ID: " + id));

        Long childId = contact.getChild().getId();

        /**
         * Checks whether another contact already
         * uses the new mobile number for this child.
         *
         * The current contact is ignored when its
         * mobile number is unchanged.
         */
        if (!contact.getMobile().equals(dto.getMobile())
                && emergencyContactRepository
                        .existsByChildIdAndMobile(childId, dto.getMobile())) {

            throw new IllegalArgumentException(
                    "Emergency contact mobile already exists for this child");
        }

        contact.setName(dto.getName());
        contact.setRelationship(dto.getRelationship());
        contact.setMobile(dto.getMobile());
        contact.setAlternateMobile(dto.getAlternateMobile());
        contact.setEmail(dto.getEmail());
        contact.setAddress(dto.getAddress());
        contact.setPriority(dto.getPriority());
        contact.setActive(dto.isActive());

        EmergencyContact updatedContact = emergencyContactRepository.save(contact);

        return convertToResponse(updatedContact);
    }

    /**
     * Deletes an emergency contact.
     */
    @Override
    @Transactional
    public void deleteContact(Long id) {

        if (!emergencyContactRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Emergency contact not found with ID: " + id);
        }

        emergencyContactRepository.deleteById(id);
    }

    /**
     * Converts an EmergencyContact entity into
     * an EmergencyContactResponseDto.
     */
    private EmergencyContactResponseDto convertToResponse(
            EmergencyContact contact) {

        Child child = contact.getChild();

        String childName = child.getFirstName() + " " + child.getLastName();

        return new EmergencyContactResponseDto(
                contact.getId(),
                child.getId(),
                childName,
                contact.getName(),
                contact.getRelationship(),
                contact.getMobile(),
                contact.getAlternateMobile(),
                contact.getEmail(),
                contact.getAddress(),
                contact.getPriority(),
                contact.isActive(),
                contact.getCreatedAt(),
                contact.getUpdatedAt());
    }
}
