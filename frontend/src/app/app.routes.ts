import { Routes } from '@angular/router';

import { authGuard } from './core/auth/guards/auth.guard';

export const routes: Routes = [

  {
    path: 'login',

    loadComponent: () =>

      import('./domains/auth/pages/login/login.component')

        .then(m => m.LoginComponent)

  },

  {

    path: '',

    loadComponent: () =>

      import('./core/layout/components/app-shell/app-shell.component')

        .then(m => m.AppShellComponent),

    canActivate: [

      authGuard

    ],

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
      },

      {
        path: 'habits',
        loadComponent: () =>
            import('./domains/habits/pages/habits.component')
                .then(m => m.HabitsComponent)
      },
    ]

  },

  {

    path: '**',

    redirectTo: ''

  }

];
