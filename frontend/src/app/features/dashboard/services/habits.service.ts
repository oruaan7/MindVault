import { Injectable } from '@angular/core';

import { HabitItem } from '../models/habit-item.model';

@Injectable({
    providedIn: 'root'
})
export class HabitsService {

    getTodayHabits(): HabitItem[] {

        return [

            {
                id: 1,
                title: 'Beber 2L de água',
                completed: true
            },

            {
                id: 2,
                title: 'Estudar Spring Boot',
                completed: false
            },

            {
                id: 3,
                title: 'Academia',
                completed: true
            },

            {
                id: 4,
                title: 'Ler 20 páginas',
                completed: false
            }

        ];

    }

}