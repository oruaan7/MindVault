import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';

import { GoalProgress } from '../models/goal-progress.model';

import { GOALS_MOCK } from '../mocks/goals.mock';

@Injectable({
    providedIn: 'root'
})
export class GoalsService {

    getGoals(): Observable<GoalProgress[]> {

        return of(GOALS_MOCK);

    }

}