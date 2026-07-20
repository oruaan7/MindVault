import { Component, inject } from '@angular/core';

import { RouterLink } from '@angular/router';

import { NavigationService } from '../../services/navigation.service';

@Component({
  selector: 'mv-sidebar',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {

  private readonly navigation =
    inject(NavigationService);

  readonly items =
    this.navigation.getItems();

}