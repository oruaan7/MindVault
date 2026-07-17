import {
  Component,
  OnInit,
  inject
} from '@angular/core';

import { CommonModule } from '@angular/common';

import { HabitsStateService } from '../services/habits-state.service';
import { HabitCardComponent } from '../../../shared/ui/habit-card/habit-card.component';

@Component({
  selector: 'app-habits',
  standalone: true,
  imports: [
    CommonModule,
    HabitCardComponent
  ],
  templateUrl: './habits.component.html',
  styleUrl: './habits.component.scss'
})
export class HabitsComponent implements OnInit {

  private readonly state =
    inject(HabitsStateService);

  readonly habits =
    this.state.habits;

  readonly loading =
    this.state.loading;

  ngOnInit(): void {

    this.state.load();

  }

}
