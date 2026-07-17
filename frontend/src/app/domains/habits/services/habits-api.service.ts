import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';

import { HabitResponse } from '../dtos/habit-response.dto';
import { CreateHabitRequest } from '../dtos/create-habit-request.dto';
import { UpdateHabitRequest } from '../dtos/update-habit-request.dto';

@Injectable({
  providedIn: 'root'
})
export class HabitsApiService {

  private readonly http = inject(HttpClient);

  private readonly apiUrl =
    `${environment.apiUrl}/habits`;

  findAll(): Observable<HabitResponse[]> {

    return this.http.get<HabitResponse[]>(
      this.apiUrl
    );

  }

  create(
    request: CreateHabitRequest
  ): Observable<HabitResponse> {

    return this.http.post<HabitResponse>(
      this.apiUrl,
      request
    );

  }

  update(
    id: string,
    request: UpdateHabitRequest
  ): Observable<HabitResponse> {

    return this.http.put<HabitResponse>(
      `${this.apiUrl}/${id}`,
      request
    );

  }

  delete(
    id: string
  ): Observable<void> {

    return this.http.delete<void>(
      `${this.apiUrl}/${id}`
    );

  }

}
