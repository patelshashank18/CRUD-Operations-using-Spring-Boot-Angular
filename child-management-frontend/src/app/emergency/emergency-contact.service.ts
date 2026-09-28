import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { EmergencyContact } from './emergency-contact.model';

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
 * Service used to manage emergency contacts.
 */
@Injectable({
  providedIn: 'root',
})
export class EmergencyContactService {
  private readonly apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  /**
   * Gets emergency contacts for a child.
   *
   * @param childId child ID
   * @returns emergency contacts
   */
  getContactsByChild(
    childId: number,
  ): Observable<ApiResponse<EmergencyContact[]>> {
    return this.http.get<ApiResponse<EmergencyContact[]>>(
      `${this.apiUrl}/children/${childId}/emergency-contacts`,
    );
  }

  /**
   * Gets one emergency contact.
   *
   * @param id emergency contact ID
   * @returns emergency contact
   */
  getContactById(id: number): Observable<ApiResponse<EmergencyContact>> {
    return this.http.get<ApiResponse<EmergencyContact>>(
      `${this.apiUrl}/emergency-contacts/${id}`,
    );
  }

  /**
   * Creates an emergency contact for a child.
   *
   * @param childId child ID
   * @param contact emergency contact information
   * @returns created emergency contact
   */
  createContact(
    childId: number,
    contact: EmergencyContact,
  ): Observable<ApiResponse<EmergencyContact>> {
    return this.http.post<ApiResponse<EmergencyContact>>(
      `${this.apiUrl}/children/${childId}/emergency-contacts`,
      contact,
    );
  }

  /**
   * Updates an emergency contact.
   *
   * @param id emergency contact ID
   * @param contact updated contact information
   * @returns updated emergency contact
   */
  updateContact(
    id: number,
    contact: EmergencyContact,
  ): Observable<ApiResponse<EmergencyContact>> {
    return this.http.put<ApiResponse<EmergencyContact>>(
      `${this.apiUrl}/emergency-contacts/${id}`,
      contact,
    );
  }

  /**
   * Deletes an emergency contact.
   *
   * @param id contact ID
   * @returns API response
   */
  deleteContact(id: number): Observable<ApiResponse<void>> {
    return this.http.delete<ApiResponse<void>>(
      `${this.apiUrl}/emergency-contacts/${id}`,
    );
  }
}
