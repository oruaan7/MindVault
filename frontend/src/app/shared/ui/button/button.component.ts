import { Component, input, output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-button',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './button.component.html',
  styleUrl: './button.component.scss'
})
export class ButtonComponent {

  readonly text = input.required<string>();

  readonly type = input<'button' | 'submit'>('button');

  readonly variant = input<'primary' | 'secondary' | 'danger'>('primary');

  readonly disabled = input(false);

  readonly pressed = output<void>();

  click(): void {

    if (this.disabled()) {

      return;

    }

    this.pressed.emit();

  }

}
