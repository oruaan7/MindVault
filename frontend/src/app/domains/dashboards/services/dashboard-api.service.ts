import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';
import { DashboardResponse } from '../dtos/dashboard-response.dto';

@Injectable({
  providedIn: 'root'
})
export class DashboardApiService {

  private readonly http = inject(HttpClient);

  private readonly apiUrl =
    `${environment.apiUrl}/analytics/dashboard`;

  getDashboard(): Observable<DashboardResponse> {

    return this.http.get<DashboardResponse>(
      this.apiUrl
    );

  }

}
