import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';

import { HabitItem } from '../models/habit-item.model';

import { TODAY_HABITS_MOCK } from '../mocks/today-habits.mock';

@Injectable({
    providedIn: 'root'
})
export class HabitsService {

    getTodayHabits(): Observable<HabitItem[]> {

        return of(TODAY_HABITS_MOCK);

    }

}