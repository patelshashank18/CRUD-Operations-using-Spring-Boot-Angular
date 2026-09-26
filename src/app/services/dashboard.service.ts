import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * Represents child statistics.
 */
export interface ChildStatistics {
  totalChildren: number;
  activeChildren: number;
  inactiveChildren: number;
  maleChildren: number;
  femaleChildren: number;
}

/**
 * Represents guardian statistics.
 */
export interface GuardianStatistics {
  totalGuardians: number;
  activeGuardians: number;
  inactiveGuardians: number;
}

/**
 * Represents emergency contact statistics.
 */
export interface EmergencyContactStatistics {
  totalEmergencyContacts: number;
  activeEmergencyContacts: number;
  inactiveEmergencyContacts: number;
}

/**
 * Represents the complete dashboard summary.
 */
export interface DashboardSummary {
  children: ChildStatistics;
  guardians: GuardianStatistics;
  emergencyContacts: EmergencyContactStatistics;
}

/**
 * Represents the standard API response.
 */
export interface ApiResponse<T> {
  success: boolean;
  message: string;
  data: T;
  timestamp: string;
}

/**
 * Service used to retrieve dashboard data.
 */
@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private readonly apiUrl =
    'http://localhost:8080/api/dashboard';

  constructor(
    private http: HttpClient
  ) {}

  /**
   * Retrieves the complete dashboard summary.
   *
   * @returns observable containing dashboard statistics
   */
  getDashboardSummary(): Observable<ApiResponse<DashboardSummary>> {

    return this.http.get<ApiResponse<DashboardSummary>>(
      `${this.apiUrl}/summary`
    );
  }
}