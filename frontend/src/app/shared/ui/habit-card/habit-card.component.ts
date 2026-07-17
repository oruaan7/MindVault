import { CommonModule } from '@angular/common';
import { Component, input } from '@angular/core';

import { HabitResponse } from '../../../domains/habits/dtos/habit-response.dto';

@Component({
  selector: 'app-habit-card',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './habit-card.component.html',
  styleUrl: './habit-card.component.scss'
})
export class HabitCardComponent {

  readonly habit =
    input.required<HabitResponse>();

}
