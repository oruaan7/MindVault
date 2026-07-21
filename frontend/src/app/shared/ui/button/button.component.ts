import { Component, Input } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'mv-button',
  standalone: true,
  imports: [
    MatIconModule
  ],
  templateUrl: './button.component.html',
  styleUrl: './button.component.scss'
})
export class ButtonComponent {

  @Input({ required: true })
  label!: string;

  @Input()
  icon?: string;

}