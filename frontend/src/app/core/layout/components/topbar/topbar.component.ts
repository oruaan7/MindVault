import { Component, Input } from '@angular/core';

import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'mv-topbar',
  standalone: true,
  imports: [
    MatIconModule
  ],
  templateUrl: './topbar.component.html',
  styleUrl: './topbar.component.scss'
})
export class TopbarComponent {

  @Input({ required: true })
  title!: string;

}