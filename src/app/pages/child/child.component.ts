import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { Child, ChildService } from '../../services/child.service';

/**
 * Displays children and provides child management operations.
 */
@Component({
  selector: 'app-child',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './child.component.html',
  styleUrl: './child.component.css',
})
export class ChildComponent implements OnInit {
  children: Child[] = [];
  currentPage = 0;

  pageSize = 10;

  totalPages = 0;

  totalElements = 0;

  loading = false;

  searching = false;

  errorMessage = '';

  successMessage = '';

  showAddForm = false;

  showEditForm = false;

  editingChildId: number | null = null;

  searchName = '';

  bloodGroups: string[] = ['A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'];

  newChild: Child = this.createEmptyChild();

  editChild: Child = this.createEmptyChild();

  constructor(private childService: ChildService) {}

  /**
   * Loads children when the component starts.
   */
  ngOnInit(): void {
    this.loadChildren();
  }

  /**
   * Creates an empty child object for the forms.
   *
   * @returns empty child object
   */
  createEmptyChild(): Child {
    return {
      firstName: '',
      lastName: '',
      dateOfBirth: '',
      gender: '',
      bloodGroup: '',
      status: 'ACTIVE',
      parentName: '',
      mobile: '',
      email: '',
      address: '',
    };
  }
  /**
   * Retrieves children from the backend.
   */
  loadChildren(): void {
    this.loading = true;
    this.errorMessage = '';

    this.childService.getChildren(this.currentPage, this.pageSize).subscribe({
      next: (response) => {
        this.children = response.data?.content ?? [];

        this.totalPages = response.data?.totalPages ?? 0;

        this.loading = false;
      },

      error: (error) => {
        console.error('Failed to load children:', error);
        console.error('Backend error response:', error.error);

        this.errorMessage = error.error?.message ?? 'Failed to load children.';

        this.loading = false;
      },
    });
  }
  /**
   * Loads the previous page of children.
   */
  previousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage = this.currentPage - 1;

      this.loadChildren();
    }
  }

  /**
   * Loads the next page of children.
   */
  nextPage(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage = this.currentPage + 1;

      this.loadChildren();
    }
  }

  /**
   * Searches children by partial first or last name.
   */
  searchChildren(): void {
    const name = this.searchName.trim();

    if (!name) {
      this.loadChildren();

      return;
    }

    this.searching = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.childService.searchChildren(name).subscribe({
      next: (response) => {
        console.log('Search response:', response);

        this.children = response.data ?? [];

        this.searching = false;
      },

      error: (error) => {
        console.error('Failed to search children:', error);
        console.error('Backend error response:', error.error);

        this.errorMessage =
          error.error?.message ?? 'Failed to search children.';

        this.searching = false;
      },
    });
  }

  /**
   * Clears the child search and loads all children.
   */
  clearSearch(): void {
    this.searchName = '';

    this.loadChildren();
  }

  /**
   * Opens the Add Child form.
   */
  openAddForm(): void {
    this.showAddForm = true;
    this.showEditForm = false;

    this.errorMessage = '';
    this.successMessage = '';

    this.newChild = this.createEmptyChild();
  }

  /**
   * Closes the Add Child form.
   */
  closeAddForm(): void {
    this.showAddForm = false;

    this.newChild = this.createEmptyChild();
  }

  /**
   * Saves a new child.
   */
  saveChild(): void {
    this.successMessage = '';
    this.errorMessage = '';

    this.childService.createChild(this.newChild).subscribe({
      next: (response) => {
        this.successMessage = response.message;

        this.showAddForm = false;
      
        this.newChild = this.createEmptyChild();

        this.currentPage = Math.max(this.totalPages - 1, 0);

        this.loadChildren();
      },

      error: (error) => {
        console.error('Failed to save child:', error);
        console.error('Backend error response:', error.error);

        this.errorMessage = error.error?.message ?? 'Failed to save child.';
      },
    });
  }

  /**
   * Opens the Edit Child form.
   *
   * @param id child ID
   */
  openEditForm(id: number): void {
    this.successMessage = '';
    this.errorMessage = '';

    this.showAddForm = false;
    this.showEditForm = true;

    this.editingChildId = id;

    this.childService.getChildById(id).subscribe({
      next: (response) => {
        this.editChild = {
          ...response.data,
        };
      },

      error: (error) => {
        console.error('Failed to load child:', error);
        console.error('Backend error response:', error.error);

        this.errorMessage = error.error?.message ?? 'Failed to load child.';

        this.showEditForm = false;
      },
    });
  }

  /**
   * Closes the Edit Child form.
   */
  closeEditForm(): void {
    this.showEditForm = false;

    this.editingChildId = null;

    this.editChild = this.createEmptyChild();
  }

  /**
   * Updates an existing child.
   */
  updateChild(): void {
    if (this.editingChildId === null) {
      return;
    }

    this.successMessage = '';
    this.errorMessage = '';

    this.childService
      .updateChild(this.editingChildId, this.editChild)
      .subscribe({
        next: (response) => {
          this.successMessage = response.message;

          this.showEditForm = false;

          this.editingChildId = null;

          this.editChild = this.createEmptyChild();

          this.loadChildren();
        },

        error: (error) => {
          console.error('Failed to update child:', error);
          console.error('Backend error response:', error.error);

          this.errorMessage = error.error?.message ?? 'Failed to update child.';
        },
      });
  }

  /**
   * Deletes a child from the system.
   *
   * @param id child ID
   */
  deleteChild(id: number): void {
    const confirmed = confirm('Are you sure you want to delete this child?');

    if (!confirmed) {
      return;
    }

    this.successMessage = '';
    this.errorMessage = '';

    this.childService.deleteChild(id).subscribe({
      next: (response) => {
        this.successMessage = response.message;

        this.loadChildren();
      },

      error: (error) => {
        console.error('Failed to delete child:', error);
        console.error('Backend error response:', error.error);

        this.errorMessage = error.error?.message ?? 'Failed to delete child.';
      },
    });
  }

}