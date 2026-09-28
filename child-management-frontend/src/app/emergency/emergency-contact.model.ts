/**
 * Represents an emergency contact.
 */
export interface EmergencyContact {

  id?: number;

  childId: number;

  childName: string;

  name: string;

  relationship: string;

  mobile: string;

  alternateMobile?: string;

  email?: string;

  address?: string;

  priority: number;

  active: boolean;

  createdAt?: string;

  updatedAt?: string;
}