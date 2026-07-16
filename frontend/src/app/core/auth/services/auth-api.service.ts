import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';

import { LoginRequest } from '../dtos/login-request.dto';
import { LoginResponse } from '../dtos/login-response.dto';

@Injectable({
  providedIn: 'root'
})
export class AuthApiService {

  private readonly http = inject(HttpClient);

  login(request: LoginRequest) {

    return this.http.post<LoginResponse>(
        `${environment.apiUrl}/auth/login`,
        request
    );

}

}
