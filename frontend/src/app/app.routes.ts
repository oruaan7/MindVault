import { Routes } from '@angular/router';

import { AppLayoutComponent } from './core/layout/layouts/app-layout/app-layout.component';
import { DashboardPageComponent } from './features/dashboard/pages/dashboard-page/dashboard-page.component';

export const routes: Routes = [
  {
    path: '',
    component: AppLayoutComponent,
    children: [
      {
        path: '',
        component: DashboardPageComponent
      }
    ]
  }
];