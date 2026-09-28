import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * Represents a child in ChildCare360.
 */
export interface Child {
  id?: number;
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  gender: string;
  bloodGroup: string;
  status: string;
  parentName: string;
  mobile: string;
  email: string;
  address: string;
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
 * Represents paginated child data.
 */
export interface PaginationResponse<T> {
  content: T[];
  pageNumber: number;
  pageSize: number;
  totalElements: number;
  totalPages: number;
  last: boolean;
}

/**
 * Service used to communicate with child APIs.
 */
@Injectable({
  providedIn: 'root'
})
export class ChildService {

  private readonly apiUrl =
    'http://localhost:8080/api/children';

  constructor(
    private http: HttpClient
  ) {}

 /**
 * Retrieves children with pagination.
 *
 * @param page page number
 * @param size number of children per page
 * @returns observable containing paginated children
 */
getChildren(
    page: number = 0,
    size: number = 5
): Observable<ApiResponse<PaginationResponse<Child>>> {

    return this.http.get<ApiResponse<PaginationResponse<Child>>>(
        `${this.apiUrl}?page=${page}&size=${size}`
    );
}
  /**
   * Retrieves a child by ID.
   *
   * @param id child ID
   * @returns observable containing the child
   */
  getChildById(
    id: number
  ): Observable<ApiResponse<Child>> {

    return this.http.get<ApiResponse<Child>>(
      `${this.apiUrl}/${id}`
    );
  }

  /**
   * Searches children by partial first or last name.
   *
   * @param name name text to search
   * @returns observable containing matching children
   */
  searchChildren(
    name: string
  ): Observable<ApiResponse<Child[]>> {

    return this.http.get<ApiResponse<Child[]>>(
      `${this.apiUrl}/search?name=${encodeURIComponent(name)}`
    );
  }

  /**
   * Creates a new child.
   *
   * @param child child information
   * @returns observable containing the saved child
   */
  createChild(
    child: Child
  ): Observable<ApiResponse<Child>> {

    return this.http.post<ApiResponse<Child>>(
      this.apiUrl,
      child
    );
  }

  /**
   * Updates an existing child.
   *
   * @param id child ID
   * @param child updated child information
   * @returns observable containing the updated child
   */
  updateChild(
    id: number,
    child: Child
  ): Observable<ApiResponse<Child>> {

    return this.http.put<ApiResponse<Child>>(
      `${this.apiUrl}/${id}`,
      child
    );
  }

  /**
   * Deletes a child.
   *
   * @param id child ID
   * @returns observable containing the API response
   */
  deleteChild(
    id: number
  ): Observable<ApiResponse<void>> {

    return this.http.delete<ApiResponse<void>>(
      `${this.apiUrl}/${id}`
    );
  }
}