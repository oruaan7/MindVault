import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthStateService } from '../../../auth/services/auth-state.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

  readonly authState = inject(AuthStateService);

  logout(): void {

    this.authState.logout();

  }

}
