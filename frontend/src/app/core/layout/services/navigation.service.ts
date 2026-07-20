import { Injectable } from '@angular/core';

import { NavigationItem } from '../models/navigation-item.model';

@Injectable({
  providedIn: 'root'
})
export class NavigationService {

  getItems(): NavigationItem[] {

    return [

      {
        label: 'Dashboard',
        route: '/dashboard',
        icon: 'dashboard'
      },

      {
        label: 'Hábitos',
        route: '/habits',
        icon: 'check_circle'
      },

      {
        label: 'Metas',
        route: '/goals',
        icon: 'flag'
      },

      {
        label: 'Projetos',
        route: '/projects',
        icon: 'folder'
      },

      {
        label: 'Notas',
        route: '/notes',
        icon: 'description'
      }

    ];

  }

}