import { Injectable, computed, inject, signal } from '@angular/core';
import { Router } from '@angular/router';

import { AuthApiService } from './auth-api.service';
import { LoginRequest } from '../dtos/login-request.dto';
import { TokenStorageService } from '../storage/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthStateService {

  private readonly authApi = inject(AuthApiService);
  private readonly tokenStorage = inject(TokenStorageService);
  private readonly router = inject(Router);

  private readonly tokenSignal = signal<string | null>(
    this.tokenStorage.getToken()
  );

  private readonly loadingSignal = signal(false);

  private readonly errorSignal = signal<string | null>(null);

  readonly token = this.tokenSignal.asReadonly();

  readonly loading = this.loadingSignal.asReadonly();

  readonly error = this.errorSignal.asReadonly();

  readonly authenticated = computed(() => this.token() !== null);

  login(request: LoginRequest): void {

    this.loadingSignal.set(true);
    this.errorSignal.set(null);

    this.authApi.login(request).subscribe({

      next: response => {

        this.tokenStorage.saveToken(response.token);

        this.tokenSignal.set(response.token);

        this.loadingSignal.set(false);

        this.router.navigate(['/dashboard']);

      },

      error: () => {

        this.loadingSignal.set(false);

        this.errorSignal.set('Email ou senha inválidos.');

      }

    });

  }

  logout(): void {

    this.tokenStorage.removeToken();

    this.tokenSignal.set(null);

    this.router.navigate(['/login']);

  }

}
