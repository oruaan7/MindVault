import { Injectable, inject, signal } from '@angular/core';

import { DashboardApiService } from './dashboard-api.service';
import { DashboardResponse } from '../dtos/dashboard-response.dto';

@Injectable({
  providedIn: 'root'
})
export class DashboardStateService {

  private readonly api = inject(DashboardApiService);

  private readonly dashboardSignal =
    signal<DashboardResponse | null>(null);

  private readonly loadingSignal =
    signal(false);

  readonly dashboard =
    this.dashboardSignal.asReadonly();

  readonly loading =
    this.loadingSignal.asReadonly();

  load(): void {

    this.loadingSignal.set(true);

    this.api.getDashboard().subscribe({

      next: response => {

        this.dashboardSignal.set(response);

        this.loadingSignal.set(false);

      },

      error: error => {

        console.error(error);

        this.loadingSignal.set(false);

      }

    });

  }

}
