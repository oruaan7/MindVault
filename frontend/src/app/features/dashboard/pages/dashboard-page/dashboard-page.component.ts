import { Component, OnInit } from '@angular/core';
import { TopbarComponent } from '../../../../core/layout/components/topbar/topbar.component';
import { StatCardComponent } from '../../components/stat-card/stat-card.component';
import { QuickActionsComponent } from '../../components/quick-actions/quick-actions.component';
import { DashboardService } from '../../services/dashboard.service';
import { DashboardStats } from '../../models/dashboard-stats.model';
import { TodayHabitsComponent } from '../../components/today-habits/today-habits.component';
import { HabitsService } from '../../services/habits.service';
import { HabitItem } from '../../models/habit-item.model';
import { GoalProgressComponent } from '../../components/goal-progress/goal-progress.component';
import { GoalsService } from '../../services/goals.service';
import { GoalProgress } from '../../models/goal-progress.model';
import { HeroSectionComponent } from '../../components/hero-section/hero-section.component';


@Component({
  selector: 'mv-dashboard-page',
  standalone: true,
  templateUrl: './dashboard-page.component.html',
  styleUrl: './dashboard-page.component.scss',
  imports: [
    TopbarComponent,
    StatCardComponent,
    QuickActionsComponent,
    TodayHabitsComponent,
    GoalProgressComponent,
    HeroSectionComponent
  ]
})

export class DashboardPageComponent implements OnInit {

    stats!: DashboardStats;

    habits!: HabitItem[];

    goals!: GoalProgress[];

    constructor(

        private readonly dashboardService: DashboardService,

        private readonly habitsService: HabitsService,

        private readonly goalsService: GoalsService

    ) {}

    ngOnInit(): void {

        this.dashboardService.getStats().subscribe(stats => {

        this.stats = stats;

});

    this.habitsService.getTodayHabits().subscribe(habits => {

    this.habits = habits;

});

this.goalsService.getGoals().subscribe(goals => {

    this.goals = goals;

});

    }

}