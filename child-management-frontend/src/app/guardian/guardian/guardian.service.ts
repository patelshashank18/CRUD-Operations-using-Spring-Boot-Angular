import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Guardian } from './guardian.model';

/**
 * Service used for Guardian API operations.
 */
@Injectable({
  providedIn: 'root'
})
export class GuardianService {

  /**
   * Spring Boot Guardian API URL.
   */
  private apiUrl = 'http://localhost:8080/api/guardians';

  constructor(private http: HttpClient) {}

  /**
   * Gets paginated guardians.
   *
   * @param page page number
   * @param size number of guardians per page
   */
  getAllGuardians(
    page: number = 0,
    size: number = 10
  ): Observable<any> {

    const params = new HttpParams()
      .set('page', page)
      .set('size', size);

    return this.http.get<any>(
      this.apiUrl,
      { params }
    );
  }

  /**
   * Gets a guardian by ID.
   *
   * @param id guardian ID
   */
  getGuardianById(id: number): Observable<any> {

    return this.http.get<any>(
      `${this.apiUrl}/${id}`
    );
  }

  /**
   * Creates a new guardian.
   *
   * @param guardian guardian details
   */
  createGuardian(guardian: Guardian): Observable<any> {

    return this.http.post<any>(
      this.apiUrl,
      guardian
    );
  }

  /**
   * Updates an existing guardian.
   *
   * @param id guardian ID
   * @param guardian guardian details
   */
  updateGuardian(
    id: number,
    guardian: Guardian
  ): Observable<any> {

    return this.http.put<any>(
      `${this.apiUrl}/${id}`,
      guardian
    );
  }

  /**
   * Deletes a guardian.
   *
   * @param id guardian ID
   */
  deleteGuardian(id: number): Observable<any> {

    return this.http.delete<any>(
      `${this.apiUrl}/${id}`
    );
  }
}