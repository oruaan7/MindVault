import { Component, OnInit } from '@angular/core';
import { TopbarComponent } from '../../../../core/layout/components/topbar/topbar.component';
import { StatCardComponent } from '../../components/stat-card/stat-card.component';
import { QuickActionsComponent } from '../../components/quick-actions/quick-actions.component';
import { DashboardService } from '../../services/dashboard.service';
import { DashboardStats } from '../../models/dashboard-stats.model';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'mv-dashboard-page',
  standalone: true,
  templateUrl: './dashboard-page.component.html',
  styleUrl: './dashboard-page.component.scss',
  imports: [
    TopbarComponent,
    StatCardComponent,
    QuickActionsComponent
  ]
})
export class DashboardPageComponent implements OnInit {

    stats!: DashboardStats;

    constructor(
        private readonly dashboardService: DashboardService
    ) {}

    ngOnInit(): void {

        this.stats = this.dashboardService.getStats(); Observable<DashboardStats>;

    }

}