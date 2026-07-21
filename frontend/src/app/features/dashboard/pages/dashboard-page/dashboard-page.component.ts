import { Component } from '@angular/core';
import { TopbarComponent } from '../../../../core/layout/components/topbar/topbar.component';

@Component({
  selector: 'mv-dashboard-page',
  standalone: true,
  templateUrl: './dashboard-page.component.html',
  styleUrl: './dashboard-page.component.scss',
  imports: [
    TopbarComponent
  ]
})
export class DashboardPageComponent {}