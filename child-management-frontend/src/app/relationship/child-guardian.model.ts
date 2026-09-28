/**
 * Represents a guardian connected to a child.
 */
export interface ChildGuardian {
  guardianId: number;
  firstName: string;
  lastName: string;
  relationship: string;
  mobile: string;
  email?: string;
  active: boolean;
}   