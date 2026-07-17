import { Injectable, inject, signal } from '@angular/core';

import { HabitResponse } from '../dtos/habit-response.dto';

import { HabitsApiService } from './habits-api.service';

@Injectable({
  providedIn: 'root'
})
export class HabitsStateService {

  private readonly api =
    inject(HabitsApiService);

  private readonly habitsSignal =
    signal<HabitResponse[]>([]);

  private readonly loadingSignal =
    signal(false);

  readonly habits =
    this.habitsSignal.asReadonly();

  readonly loading =
    this.loadingSignal.asReadonly();

  load(): void {

    this.loadingSignal.set(true);

    this.api.findAll().subscribe({

      next: habits => {

        this.habitsSignal.set(habits);

        this.loadingSignal.set(false);

      },

      error: error => {

        console.error(error);

        this.loadingSignal.set(false);

      }

    });

  }

}
