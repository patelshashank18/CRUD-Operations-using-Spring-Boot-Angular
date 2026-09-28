import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { Child, ChildService } from '../../services/child.service';

import { ChildGuardian } from '../../relationship/child-guardian.model';

import { ChildGuardianService } from '../../relationship/child-guardian.service';

import { EmergencyContact } from '../emergency-contact.model';

import { EmergencyContactService } from '../emergency-contact.service';
/**
 * Manages emergency contacts for children.
 */
@Component({
  selector: 'app-emergency-contact',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './emergency-contact.component.html',
  styleUrl: './emergency-contact.component.css',
})
export class EmergencyContactComponent implements OnInit {
  children: Child[] = [];

  guardians: ChildGuardian[] = [];

  contacts: EmergencyContact[] = [];

  selectedChildId: number | null = null;

  selectedGuardianId: number | null = null;

  showForm = false;

  editingContactId: number | null = null;

  loading = false;

  errorMessage = '';

  successMessage = '';

  contact: EmergencyContact = this.createEmptyContact();

  constructor(
    private childService: ChildService,
    private childGuardianService: ChildGuardianService,
    private emergencyContactService: EmergencyContactService,
  ) {}

  ngOnInit(): void {
    this.loadChildren();
  }

  /**
   * Creates an empty emergency contact object.
   *
   * @returns empty emergency contact
   */
  createEmptyContact(): EmergencyContact {
    return {
      childId: 0,
      childName: '',
      name: '',
      relationship: '',
      mobile: '',
      alternateMobile: '',
      email: '',
      address: '',
      priority: 1,
      active: true,
    };
  }

  /**
   * Loads all children.
   */
  loadChildren(): void {
    this.childService.getChildren(0, 100).subscribe({
      next: (response) => {
        this.children = response.data?.content ?? [];
      },

      error: (error) => {
        console.error('Failed to load children:', error);

        this.errorMessage = error.error?.message ?? 'Failed to load children.';
      },
    });
  }

  /**
   * Loads guardians and emergency contacts
   * when a child is selected.
   */
  loadChildData(): void {
    this.guardians = [];

    this.contacts = [];

    this.selectedGuardianId = null;

    this.showForm = false;

    this.editingContactId = null;

    this.errorMessage = '';

    this.successMessage = '';

    if (this.selectedChildId === null) {
      return;
    }

    this.loadGuardians();

    this.loadContacts();
  }

  /**
   * Loads guardians connected to the selected child.
   */
  loadGuardians(): void {
    if (this.selectedChildId === null) {
      return;
    }

    this.childGuardianService
      .getGuardiansByChild(this.selectedChildId)
      .subscribe({
        next: (response) => {
          this.guardians = response.data ?? [];
        },

        error: (error) => {
          console.error('Failed to load guardians:', error);

          this.errorMessage =
            error.error?.message ?? 'Failed to load guardians.';
        },
      });
  }

  /**
   * Loads emergency contacts for the selected child.
   */
  loadContacts(): void {
    if (this.selectedChildId === null) {
      return;
    }

    this.loading = true;

    this.emergencyContactService
      .getContactsByChild(this.selectedChildId)
      .subscribe({
        next: (response) => {
          this.contacts = response.data ?? [];

          this.loading = false;
        },

        error: (error) => {
          console.error('Failed to load emergency contacts:', error);

          this.errorMessage =
            error.error?.message ?? 'Failed to load emergency contacts.';

          this.loading = false;
        },
      });
  }

  /**
   * Opens the form for adding a contact.
   */
  openAddForm(): void {
    if (this.selectedChildId === null) {
      return;
    }

    this.editingContactId = null;

    this.contact = this.createEmptyContact();

    this.contact.childId = this.selectedChildId;

    this.showForm = true;

    this.errorMessage = '';

    this.successMessage = '';
  }

  /**
   * Fills the emergency contact form
   * using the selected guardian.
   */
  selectGuardian(): void {
    const guardian = this.guardians.find(
      (item) => item.guardianId === this.selectedGuardianId,
    );

    if (!guardian) {
      return;
    }

    this.contact.name = `${guardian.firstName} ${guardian.lastName}`;

    this.contact.relationship = guardian.relationship;

    this.contact.mobile = guardian.mobile;

    this.contact.email = guardian.email ?? '';
  }

  /**
   * Saves a new emergency contact.
   */
  saveContact(): void {
    if (this.selectedChildId === null) {
      this.errorMessage = 'Please select a child.';

      return;
    }

    this.errorMessage = '';

    this.successMessage = '';

    this.contact.childId = this.selectedChildId;

    this.emergencyContactService
      .createContact(this.selectedChildId, this.contact)
      .subscribe({
        next: (response) => {
          this.successMessage = response.message;

          this.showForm = false;

          this.loadContacts();
        },

        error: (error) => {
          console.error('Failed to create emergency contact:', error);

          this.errorMessage =
            error.error?.message ?? 'Failed to create emergency contact.';
        },
      });
  }

  /**
   * Opens the form for editing a contact.
   *
   * @param contact contact to edit
   */
  editContact(contact: EmergencyContact): void {
    this.editingContactId = contact.id ?? null;

    this.contact = {
      ...contact,
    };

    this.showForm = true;

    this.errorMessage = '';

    this.successMessage = '';
  }

  /**
   * Updates an existing emergency contact.
   */
  updateContact(): void {
    if (this.editingContactId === null) {
      return;
    }

    this.errorMessage = '';

    this.successMessage = '';

    this.emergencyContactService
      .updateContact(this.editingContactId, this.contact)
      .subscribe({
        next: (response) => {
          this.successMessage = response.message;

          this.showForm = false;

          this.editingContactId = null;

          this.loadContacts();
        },

        error: (error) => {
          console.error('Failed to update emergency contact:', error);

          this.errorMessage =
            error.error?.message ?? 'Failed to update emergency contact.';
        },
      });
  }

  /**
   * Deletes an emergency contact.
   *
   * @param id contact ID
   */
  deleteContact(id: number): void {
    const confirmed = confirm(
      'Are you sure you want to delete this emergency contact?',
    );

    if (!confirmed) {
      return;
    }

    this.errorMessage = '';

    this.successMessage = '';

    this.emergencyContactService.deleteContact(id).subscribe({
      next: (response) => {
        this.successMessage = response.message;

        this.loadContacts();
      },

      error: (error) => {
        console.error('Failed to delete emergency contact:', error);

        this.errorMessage =
          error.error?.message ?? 'Failed to delete emergency contact.';
      },
    });
  }

  /**
   * Cancels the add or edit form.
   */
  cancelForm(): void {
    this.showForm = false;

    this.editingContactId = null;

    this.selectedGuardianId = null;

    this.contact = this.createEmptyContact();
  }
}
