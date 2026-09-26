import { Component, OnInit } from '@angular/core';

import {
  DashboardService,
  DashboardSummary
} from '../../services/dashboard.service';

/**
 * Displays ChildCare360 dashboard statistics.
 */
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  dashboard: DashboardSummary | null = null;

  loading = false;

  errorMessage = '';

  constructor(
    private dashboardService: DashboardService
  ) {}

  ngOnInit(): void {
    this.loadDashboard();
  }

  /**
   * Loads dashboard statistics from the backend.
   */
  loadDashboard(): void {

    this.loading = true;
    this.errorMessage = '';

    this.dashboardService
      .getDashboardSummary()
      .subscribe({
        next: (response) => {

          this.dashboard = response.data;

          this.loading = false;
        },

        error: (error) => {

          console.error(
            'Failed to load dashboard:',
            error
          );

          this.errorMessage =
            error.error?.message ??
            'Failed to load dashboard.';

          this.loading = false;
        }
      });
  }
}