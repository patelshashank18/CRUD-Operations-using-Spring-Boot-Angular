package com.example.child_management.guardian.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.child_management.exception.ResourceNotFoundException;
import com.example.child_management.guardian.dto.GuardianDto;
import com.example.child_management.guardian.dto.GuardianResponseDto;
import com.example.child_management.guardian.entity.Guardian;
import com.example.child_management.guardian.repository.GuardianRepository;
import com.example.child_management.guardian.service.GuardianService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of GuardianService.
 *
 * Contains business logic for Guardian management.
 */
@Service
@RequiredArgsConstructor
public class GuardianServiceImpl implements GuardianService {

    private final GuardianRepository guardianRepository;

    /**
     * Creates a guardian.
     */
    @Override
    @Transactional
    public GuardianResponseDto createGuardian(
            GuardianDto dto) {

        Guardian guardian = new Guardian();

        guardian.setFirstName(dto.getFirstName());
        guardian.setLastName(dto.getLastName());
        guardian.setRelationship(dto.getRelationship());
        guardian.setMobile(dto.getMobile());
        guardian.setEmail(dto.getEmail());
        guardian.setAddress(dto.getAddress());
        guardian.setActive(dto.isActive());

        Guardian savedGuardian = guardianRepository.save(guardian);

        return convertToResponse(savedGuardian);
    }

    /**
     * Gets all guardians.
     */
    @Override
    @Transactional(readOnly = true)
    public List<GuardianResponseDto> getAllGuardians() {

        return guardianRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    /**
     * Gets one guardian by ID.
     */
    @Override
    @Transactional(readOnly = true)
    public GuardianResponseDto getGuardianById(
            Long id) {

        Guardian guardian = guardianRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Guardian not found with ID: "
                                + id));

        return convertToResponse(guardian);
    }

    /**
     * Updates a guardian.
     */
    @Override
    @Transactional
    public GuardianResponseDto updateGuardian(
            Long id,
            GuardianDto dto) {

        Guardian guardian = guardianRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Guardian not found with ID: "
                                + id));

        guardian.setFirstName(dto.getFirstName());
        guardian.setLastName(dto.getLastName());
        guardian.setRelationship(dto.getRelationship());
        guardian.setMobile(dto.getMobile());
        guardian.setEmail(dto.getEmail());
        guardian.setAddress(dto.getAddress());
        guardian.setActive(dto.isActive());

        Guardian updatedGuardian = guardianRepository.save(guardian);

        return convertToResponse(updatedGuardian);
    }

    /**
     * Deletes a guardian.
     */
    @Override
    @Transactional
    public void deleteGuardian(Long id) {

        if (!guardianRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Guardian not found with ID: " + id);
        }

        guardianRepository.deleteById(id);
    }

    /**
     * Converts Guardian entity into
     * GuardianResponseDto.
     */
    private GuardianResponseDto convertToResponse(
            Guardian guardian) {

        return new GuardianResponseDto(
                guardian.getId(),
                guardian.getFirstName(),
                guardian.getLastName(),
                guardian.getRelationship(),
                guardian.getMobile(),
                guardian.getEmail(),
                guardian.getAddress(),
                guardian.isActive(),
                guardian.getCreatedAt(),
                guardian.getUpdatedAt());
    }
}
