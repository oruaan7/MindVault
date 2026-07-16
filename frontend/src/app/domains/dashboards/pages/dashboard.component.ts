import { Component, OnInit, inject } from '@angular/core';

import { StatCardComponent } from '../../../shared/ui/stat-card/stat-card.component';

import { DashboardStateService } from '../services/dashboard-state.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [

    StatCardComponent

  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {

  readonly state = inject(DashboardStateService);

  ngOnInit(): void {

    this.state.load();

  }

}
