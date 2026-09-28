import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

import {
  Child,
  ChildService
} from '../../services/child.service';

import { Guardian } from '../../guardian/guardian.model';
import { GuardianService } from '../../guardian/guardian.service';

import { ChildGuardian } from '../child-guardian.model';
import { ChildGuardianService } from '../child-guardian.service';
/**
 * Manages relationships between children and guardians.
 */
@Component({
  selector: 'app-child-guardian',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './child-guardian.component.html',
  styleUrl: './child-guardian.component.css'
})
export class ChildGuardianComponent implements OnInit {

  children: Child[] = [];

  guardians: Guardian[] = [];

  connectedGuardians: ChildGuardian[] = [];

  selectedChildId: number | null = null;

  selectedGuardianId: number | null = null;

  loading = false;

  errorMessage = '';

  successMessage = '';

  constructor(
    private childService: ChildService,
    private guardianService: GuardianService,
    private childGuardianService: ChildGuardianService
  ) {}

  /**
   * Loads children and guardians when the component starts.
   */
  ngOnInit(): void {
    this.loadChildren();
    this.loadGuardians();
  }

  /**
   * Loads the first page of children.
   */
  loadChildren(): void {
    this.childService
      .getChildren(0, 100)
      .subscribe({
        next: (response) => {
          this.children = response.data?.content ?? [];
        },

        error: (error) => {
          console.error('Failed to load children:', error);

          this.errorMessage =
            error.error?.message ??
            'Failed to load children.';
        }
      });
  }

  /**
   * Loads all guardians.
   */
  loadGuardians(): void {
    this.guardianService
      .getAllGuardians()
      .subscribe({
        next: (response) => {
          this.guardians = response.data ?? [];
        },

        error: (error) => {
          console.error('Failed to load guardians:', error);

          this.errorMessage =
            error.error?.message ??
            'Failed to load guardians.';
        }
      });
  }

  /**
   * Loads guardians connected to the selected child.
   */
  loadConnectedGuardians(): void {

    if (this.selectedChildId === null) {
      this.connectedGuardians = [];
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    this.childGuardianService
      .getGuardiansByChild(this.selectedChildId)
      .subscribe({
        next: (response) => {
          this.connectedGuardians =
            response.data ?? [];

          this.loading = false;
        },

        error: (error) => {
          console.error(
            'Failed to load connected guardians:',
            error
          );

          this.errorMessage =
            error.error?.message ??
            'Failed to load connected guardians.';

          this.loading = false;
        }
      });
  }

  /**
   * Connects the selected guardian to the selected child.
   */
  addGuardian(): void {

    if (
      this.selectedChildId === null ||
      this.selectedGuardianId === null
    ) {
      this.errorMessage =
        'Please select a child and guardian.';

      return;
    }

    this.errorMessage = '';
    this.successMessage = '';

    this.childGuardianService
      .addGuardianToChild(
        this.selectedChildId,
        this.selectedGuardianId
      )
      .subscribe({
        next: (response) => {
          this.successMessage = response.message;

          this.selectedGuardianId = null;

          this.loadConnectedGuardians();
        },

        error: (error) => {
          console.error(
            'Failed to connect guardian:',
            error
          );

          this.errorMessage =
            error.error?.message ??
            'Failed to connect guardian.';
        }
      });
  }

  /**
   * Removes a guardian from the selected child.
   *
   * @param guardianId guardian ID
   */
  removeGuardian(guardianId: number): void {

    if (this.selectedChildId === null) {
      return;
    }

    const confirmed = confirm(
      'Are you sure you want to remove this guardian from the child?'
    );

    if (!confirmed) {
      return;
    }

    this.errorMessage = '';
    this.successMessage = '';

    this.childGuardianService
      .removeGuardianFromChild(
        this.selectedChildId,
        guardianId
      )
      .subscribe({
        next: (response) => {
          this.successMessage = response.message;

          this.loadConnectedGuardians();
        },

        error: (error) => {
          console.error(
            'Failed to remove guardian:',
            error
          );

          this.errorMessage =
            error.error?.message ??
            'Failed to remove guardian.';
        }
      });
  }
}