/**
 * Represents Guardian information.
 */
export interface Guardian {

  /**
   * Guardian ID
   */
  id?: number;

  /**
   * Guardian first name.
   */
  firstName: string;

  /**
   * Guardian last name.
   */
  lastName: string;

  /**
   * Relationship with the child.
   */
  relationship: string;

  /**
   * Guardian mobile number.
   */
  mobile: string;

  /**
   * Guardian email address.
   */
  email?: string;

  /**
   * Guardian address.
   */
  address?: string;

  /**
   * Indicates whether the guardian is active.
   */
  active: boolean;

  /**
   * Guardian creation time.
   */
  createdAt?: string;

  /**
   * Guardian last update time.
   */
  updatedAt?: string;
}
