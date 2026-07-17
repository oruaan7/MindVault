import { ButtonComponent } from '../../../../shared/ui/button/button.component';
import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { Router } from '@angular/router';

import { AuthStateService } from '../../../../core/auth/services/auth-state.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ButtonComponent
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  private readonly fb = inject(FormBuilder);
  private readonly router = inject(Router);

  readonly authState = inject(AuthStateService);

  readonly form = this.fb.nonNullable.group({

    email: ['', [Validators.required, Validators.email]],

    password: ['', [Validators.required]]

  });

  constructor() {

    if (this.authState.authenticated()) {

      this.router.navigate(['/dashboard']);

    }

  }

  login(): void {

    if (this.form.invalid) {

      this.form.markAllAsTouched();

      return;

    }

    this.authState.login(this.form.getRawValue());

  }

}
