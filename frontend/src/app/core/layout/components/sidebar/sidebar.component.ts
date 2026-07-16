import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {

  readonly items = [

    {
      label: 'Dashboard',
      route: '/dashboard'
    },
    {
      label: 'Hábitos',
      route: '/habits'
    },
    {
      label: 'Metas',
      route: '/goals'
    },
    {
      label: 'Projetos',
      route: '/projects'
    },
    {
      label: 'Notas',
      route: '/notes'
    },
    {
      label: 'Finanças',
      route: '/finance'
    },
    {
      label: 'Analytics',
      route: '/analytics'
    }

  ];

}
