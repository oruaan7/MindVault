import { Component } from '@angular/core';

import { ButtonComponent } from '../../../../shared/ui/button/button.component';

@Component({
  selector: 'mv-quick-actions',
  standalone: true,
  imports: [
    ButtonComponent
  ],
  templateUrl: './quick-actions.component.html',
  styleUrl: './quick-actions.component.scss'
})
export class QuickActionsComponent {}