import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { Guardian } from '../guardian.model';
import { GuardianService } from '../guardian.service';

/**
 * Displays guardians and provides guardian management operations.
 */
@Component({
  selector: 'app-guardian',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './guardian.component.html',
  styleUrl: './guardian.component.css',
})
export class GuardianComponent implements OnInit {

  guardians: Guardian[] = [];

  loading = false;

  searching = false;

  errorMessage = '';

  successMessage = '';

  showAddForm = false;

  showEditForm = false;

  editingGuardianId: number | null = null;

  searchName = '';

  newGuardian: Guardian = this.createEmptyGuardian();

  editGuardian: Guardian = this.createEmptyGuardian();

  constructor(private guardianService: GuardianService) {}

  /**
   * Loads guardians when the component starts.
   */
  ngOnInit(): void {
    this.loadGuardians();
  }

  /**
   * Creates an empty Guardian object.
   *
   * @return empty guardian
   */
  createEmptyGuardian(): Guardian {
    return {
      firstName: '',
      lastName: '',
      relationship: '',
      mobile: '',
      email: '',
      address: '',
      active: true,
    };
  }

  /**
   * Loads all guardians.
   */
  loadGuardians(): void {
    this.loading = true;
    this.errorMessage = '';

    this.guardianService
      .getAllGuardians()
      .subscribe({
        next: (response) => {
          this.guardians = response.data ?? [];
          this.loading = false;
        },

        error: (error) => {
          console.error('Failed to load guardians:', error);
          console.error('Backend error response:', error.error);

          this.errorMessage =
            error.error?.message ?? 'Failed to load guardians.';

          this.loading = false;
        },
      });
  }

  /**
   * Searches guardians by first name and last name.
   */
  searchGuardians(): void {
    const name = this.searchName.trim().toLowerCase();

    if (!name) {
      this.loadGuardians();
      return;
    }

    this.searching = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.guardianService
      .getAllGuardians()
      .subscribe({
        next: (response) => {
          const allGuardians: Guardian[] =
            response.data ?? [];

          this.guardians = allGuardians.filter((guardian) => {
            const fullName =
              `${guardian.firstName} ${guardian.lastName}`
                .toLowerCase();

            return fullName.includes(name);
          });

          this.searching = false;
        },

        error: (error) => {
          console.error('Failed to search guardians:', error);
          console.error('Backend error response:', error.error);

          this.errorMessage =
            error.error?.message ?? 'Failed to search guardians.';

          this.searching = false;
        },
      });
  }

  /**
   * Clears the guardian search.
   */
  clearSearch(): void {
    this.searchName = '';
    this.loadGuardians();
  }

  /**
   * Opens the Add Guardian form.
   */
  openAddForm(): void {
    this.showAddForm = true;
    this.showEditForm = false;

    this.editingGuardianId = null;

    this.successMessage = '';
    this.errorMessage = '';

    this.newGuardian = this.createEmptyGuardian();
  }

  /**
   * Closes the Add Guardian form.
   */
  closeAddForm(): void {
    this.showAddForm = false;
    this.newGuardian = this.createEmptyGuardian();
  }

  /**
   * Saves a new guardian.
   */
  saveGuardian(): void {
    this.successMessage = '';
    this.errorMessage = '';

    this.guardianService
      .createGuardian(this.newGuardian)
      .subscribe({
        next: (response) => {
          this.successMessage = response.message;

          this.showAddForm = false;

          this.newGuardian = this.createEmptyGuardian();

          this.loadGuardians();
        },

        error: (error) => {
          console.error('Failed to save guardian:', error);
          console.error('Backend error response:', error.error);

          this.errorMessage =
            error.error?.message ?? 'Failed to save guardian.';
        },
      });
  }

  /**
   * Opens the Edit Guardian form.
   *
   * @param id guardian ID
   */
  openEditForm(id: number): void {
    this.successMessage = '';
    this.errorMessage = '';

    this.showAddForm = false;
    this.showEditForm = true;

    this.editingGuardianId = id;

    this.guardianService
      .getGuardianById(id)
      .subscribe({
        next: (response) => {
          this.editGuardian = {
            ...response.data,
          };
        },

        error: (error) => {
          console.error('Failed to load guardian:', error);
          console.error('Backend error response:', error.error);

          this.errorMessage =
            error.error?.message ?? 'Failed to load guardian.';
        },
      });
  }

  /**
   * Closes the Edit Guardian form.
   */
  closeEditForm(): void {
    this.showEditForm = false;
    this.editingGuardianId = null;
    this.editGuardian = this.createEmptyGuardian();
  }

  /**
   * Updates the selected guardian.
   */
  updateGuardian(): void {
    if (this.editingGuardianId === null) {
      return;
    }

    this.successMessage = '';
    this.errorMessage = '';

    this.guardianService
      .updateGuardian(
        this.editingGuardianId,
        this.editGuardian
      )
      .subscribe({
        next: (response) => {
          this.successMessage = response.message;

          this.showEditForm = false;

          this.editingGuardianId = null;

          this.editGuardian = this.createEmptyGuardian();

          this.loadGuardians();
        },

        error: (error) => {
          console.error('Failed to update guardian:', error);
          console.error('Backend error response:', error.error);

          this.errorMessage =
            error.error?.message ?? 'Failed to update guardian.';
        },
      });
  }

  /**
   * Deletes a guardian.
   *
   * @param id guardian ID
   */
  deleteGuardian(id: number): void {
    this.successMessage = '';
    this.errorMessage = '';

    const confirmed = confirm(
      'Are you sure you want to delete this guardian?'
    );

    if (!confirmed) {
      return;
    }

    this.guardianService
      .deleteGuardian(id)
      .subscribe({
        next: (response) => {
          this.successMessage = response.message;

          this.loadGuardians();
        },

        error: (error) => {
          console.error('Failed to delete guardian:', error);
          console.error('Backend error response:', error.error);

          this.errorMessage =
            error.error?.message ?? 'Failed to delete guardian.';
        },
      });
  }
}