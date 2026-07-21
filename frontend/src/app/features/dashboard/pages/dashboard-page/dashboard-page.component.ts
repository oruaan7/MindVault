import { Component, OnInit } from '@angular/core';
import { TopbarComponent } from '../../../../core/layout/components/topbar/topbar.component';
import { StatCardComponent } from '../../components/stat-card/stat-card.component';
import { QuickActionsComponent } from '../../components/quick-actions/quick-actions.component';
import { DashboardService } from '../../services/dashboard.service';
import { DashboardStats } from '../../models/dashboard-stats.model';
import { TodayHabitsComponent } from '../../components/today-habits/today-habits.component';
import { HabitsService } from '../../services/habits.service';
import { HabitItem } from '../../models/habit-item.model';


@Component({
  selector: 'mv-dashboard-page',
  standalone: true,
  templateUrl: './dashboard-page.component.html',
  styleUrl: './dashboard-page.component.scss',
  imports: [
    TopbarComponent,
    StatCardComponent,
    QuickActionsComponent,
    TodayHabitsComponent
  ]
})

export class DashboardPageComponent implements OnInit {

    stats!: DashboardStats;

    habits!: HabitItem[];

    constructor(

        private readonly dashboardService: DashboardService,

        private readonly habitsService: HabitsService

    ) {}

    ngOnInit(): void {

        this.stats = this.dashboardService.getStats();

        this.habits = this.habitsService.getTodayHabits();

    }

}