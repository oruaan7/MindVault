import { Injectable } from '@angular/core';

import { DashboardStats } from '../models/dashboard-stats.model';

@Injectable({
    providedIn: 'root'
})
export class DashboardService {

    getStats(): DashboardStats {

        return {

            habits: 8,

            goals: 3,

            projects: 5,

            notes: 21

        };

    }

}