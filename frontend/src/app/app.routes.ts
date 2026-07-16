import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./core/layout/components/app-shell/app-shell.component')
        .then(m => m.AppShellComponent),
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: 'dashboard',
        loadComponent: () =>
          import('./domains/dashboards/pages/dashboard.component')
            .then(m => m.DashboardComponent)
      }
    ]
  },

  {
    path: '**',
    redirectTo: 'dashboard'
  }
];
