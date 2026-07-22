import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';

import { DashboardStats } from '../models/dashboard-stats.model';

import { DASHBOARD_STATS_MOCK } from '../mocks/dashboard-stats.mock';

@Injectable({
    providedIn: 'root'
})
export class DashboardService {

    getStats(): Observable<DashboardStats> {

        return of(DASHBOARD_STATS_MOCK);

    }

}