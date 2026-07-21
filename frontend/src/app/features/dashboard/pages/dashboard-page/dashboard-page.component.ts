import { Component } from '@angular/core';
import { TopbarComponent } from '../../../../core/layout/components/topbar/topbar.component';
import { StatCardComponent } from '../../components/stat-card/stat-card.component';
import { QuickActionsComponent } from '../../components/quick-actions/quick-actions.component';

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
export class DashboardPageComponent {}