import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  private readonly fb = inject(FormBuilder);

  readonly form = this.fb.nonNullable.group({

    email: [

      '',

      [

        Validators.required,

        Validators.email

      ]

    ],

    password: [

      '',

      [

        Validators.required,

        Validators.minLength(6)

      ]

    ]

  });

  login(): void {

    if (this.form.invalid) {

      this.form.markAllAsTouched();

      return;

    }

    console.log(this.form.getRawValue());

  }

}
