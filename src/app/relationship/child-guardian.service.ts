import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ChildGuardian } from './child-guardian.model';

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
 * Service used to manage Child-Guardian relationships.
 */
@Injectable({
  providedIn: 'root'
})
export class ChildGuardianService {

  private readonly apiUrl =
    'http://localhost:8080/api/children';

  constructor(
    private http: HttpClient
  ) {}

  /**
   * Gets all guardians connected to a child.
   *
   * @param childId child ID
   * @returns connected guardians
   */
  getGuardiansByChild(
    childId: number
  ): Observable<ApiResponse<ChildGuardian[]>> {

    return this.http.get<ApiResponse<ChildGuardian[]>>(
      `${this.apiUrl}/${childId}/guardians`
    );
  }

  /**
   * Connects a guardian to a child.
   *
   * @param childId child ID
   * @param guardianId guardian ID
   * @returns API response
   */
  addGuardianToChild(
    childId: number,
    guardianId: number
  ): Observable<ApiResponse<void>> {

    return this.http.post<ApiResponse<void>>(
      `${this.apiUrl}/${childId}/guardians/${guardianId}`,
      {}
    );
  }

  /**
   * Removes a guardian from a child.
   *
   * @param childId child ID
   * @param guardianId guardian ID
   * @returns API response
   */
  removeGuardianFromChild(
    childId: number,
    guardianId: number
  ): Observable<ApiResponse<void>> {

    return this.http.delete<ApiResponse<void>>(
      `${this.apiUrl}/${childId}/guardians/${guardianId}`
    );
  }
}