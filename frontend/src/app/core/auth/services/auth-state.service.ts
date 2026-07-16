import { Injectable, computed, inject, signal } from '@angular/core';
import { tap } from 'rxjs/operators';

import { AuthApiService } from './auth-api.service';
import { TokenStorageService } from '../storage/token-storage.service';

import { LoginRequest } from '../dtos/login-request.dto';

@Injectable({
  providedIn: 'root'
})
export class AuthStateService {

  private readonly authApiService = inject(AuthApiService);

  private readonly tokenStorage = inject(TokenStorageService);

  private readonly tokenSignal = signal<string | null>(
    this.tokenStorage.getToken()
  );

  readonly token = this.tokenSignal.asReadonly();

  readonly authenticated = computed(() => this.token() !== null);

  login(request: LoginRequest) {

    return this.authApiService.login(request).pipe(

      tap(response => {

        this.tokenStorage.saveToken(response.token);

        this.tokenSignal.set(response.token);

      })

    );

  }

  logout(): void {

    this.tokenStorage.removeToken();

    this.tokenSignal.set(null);

  }

}
