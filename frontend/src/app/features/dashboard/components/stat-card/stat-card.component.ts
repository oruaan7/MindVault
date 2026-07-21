import { Component, Input } from '@angular/core';

import { MatIconModule } from '@angular/material/icon';

import { CardComponent } from '../../../../shared/ui/card/card.component';

@Component({
  selector: 'mv-stat-card',
  standalone: true,
  imports: [
    CardComponent,
    MatIconModule
  ],
  templateUrl: './stat-card.component.html',
  styleUrl: './stat-card.component.scss'
})
export class StatCardComponent {

  @Input({ required: true })
  title!: string;

  @Input({ required: true })
  value!: number;

  @Input({ required: true })
  icon!: string;

}