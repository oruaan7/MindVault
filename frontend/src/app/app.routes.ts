import { Routes } from '@angular/router';

import { AppLayoutComponent } from './core/layout/layouts/app-layout/app-layout.component';

export const routes: Routes = [
  {
    path: '',
    component: AppLayoutComponent,
    children: [
      // Rotas das features serão adicionadas nas próximas sprints.
    ]
  }
];