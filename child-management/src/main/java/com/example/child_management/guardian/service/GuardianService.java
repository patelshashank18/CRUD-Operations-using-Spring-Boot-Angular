package com.example.child_management.guardian.service;

import java.util.List;

import com.example.child_management.guardian.dto.GuardianDto;
import com.example.child_management.guardian.dto.GuardianResponseDto;

/**
 * Service interface for Guardian management.
 */
public interface GuardianService {

    /**
     * Creates a new guardian.
     *
     * @param dto guardian input data
     * @return created guardian
     */
    GuardianResponseDto createGuardian(GuardianDto dto);

    /**
     * Gets all guardians.
     *
     * @return list of guardians
     */
    List<GuardianResponseDto> getAllGuardians();

    /**
     * Gets one guardian by ID.
     *
     * @param id guardian ID
     * @return guardian
     */
    GuardianResponseDto getGuardianById(Long id);

    /**
     * Updates a guardian.
     *
     * @param id  guardian ID
     * @param dto updated guardian data
     * @return updated guardian
     */
    GuardianResponseDto updateGuardian(
            Long id,
            GuardianDto dto);

    /**
     * Deletes a guardian.
     *
     * @param id guardian ID
     */
    void deleteGuardian(Long id);
}
