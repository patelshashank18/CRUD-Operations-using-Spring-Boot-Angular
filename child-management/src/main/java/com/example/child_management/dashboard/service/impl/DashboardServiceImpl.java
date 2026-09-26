package com.example.child_management.dashboard.service.impl;

import org.springframework.stereotype.Service;

import com.example.child_management.dashboard.dto.ChildStatisticsDto;
import com.example.child_management.dashboard.dto.DashboardSummaryDto;
import com.example.child_management.dashboard.dto.EmergencyContactStatisticsDto;
import com.example.child_management.dashboard.dto.GuardianStatisticsDto;
import com.example.child_management.dashboard.service.DashboardService;
import com.example.child_management.emergency.repository.EmergencyContactRepository;
import com.example.child_management.guardian.repository.GuardianRepository;
import com.example.child_management.repository.ChildRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of DashboardService.
 *
 * Contains business logic required to
 * calculate ChildCare360 dashboard statistics.
 */
@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
                implements DashboardService {

        private final ChildRepository childRepository;

        private final GuardianRepository guardianRepository;

        private final EmergencyContactRepository emergencyContactRepository;

        /**
         * Gets statistics about children.
         *
         * @return child statistics
         */
        @Override
        public ChildStatisticsDto getChildStatistics() {

                long totalChildren = childRepository.count();

                long activeChildren = childRepository.countByStatus("ACTIVE");

                long inactiveChildren = childRepository.countByStatus("INACTIVE");

                long maleChildren = childRepository.findByGender(
                                com.example.child_management.enums.Gender.MALE)
                                .size();

                long femaleChildren = childRepository.findByGender(
                                com.example.child_management.enums.Gender.FEMALE)
                                .size();

                return new ChildStatisticsDto(
                                totalChildren,
                                activeChildren,
                                inactiveChildren,
                                maleChildren,
                                femaleChildren);
        }

        /**
         * Gets statistics about guardians.
         *
         * @return guardian statistics
         */
        @Override
        public GuardianStatisticsDto getGuardianStatistics() {

                long totalGuardians = guardianRepository.count();

                long activeGuardians = guardianRepository.countByActive(true);

                long inactiveGuardians = guardianRepository.countByActive(false);

                return new GuardianStatisticsDto(
                                totalGuardians,
                                activeGuardians,
                                inactiveGuardians);
        }

        /**
         * Gets statistics about emergency contacts.
         *
         * @return emergency contact statistics
         */
        @Override
        public EmergencyContactStatisticsDto getEmergencyContactStatistics() {

                long totalEmergencyContacts = emergencyContactRepository.count();

                long activeEmergencyContacts = emergencyContactRepository.countByActive(true);

                long inactiveEmergencyContacts = emergencyContactRepository.countByActive(false);

                return new EmergencyContactStatisticsDto(
                                totalEmergencyContacts,
                                activeEmergencyContacts,
                                inactiveEmergencyContacts);
        }

        /**
         * Gets the complete dashboard summary.
         *
         * @return complete dashboard summary
         */
        @Override
        public DashboardSummaryDto getDashboardSummary() {

                return new DashboardSummaryDto(
                                getChildStatistics(),
                                getGuardianStatistics(),
                                getEmergencyContactStatistics());
        }
}