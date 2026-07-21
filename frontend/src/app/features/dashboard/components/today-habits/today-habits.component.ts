import { Component, Input } from '@angular/core';

import { MatIconModule } from '@angular/material/icon';

import { HabitItem } from '../../models/habit-item.model';

@Component({
    selector: 'mv-today-habits',
    standalone: true,
    imports: [
        MatIconModule
    ],
    templateUrl: './today-habits.component.html',
    styleUrl: './today-habits.component.scss'
})
export class TodayHabitsComponent {

    @Input({ required: true })

    habits!: HabitItem[];

}