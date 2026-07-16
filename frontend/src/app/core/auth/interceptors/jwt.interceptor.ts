import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';

import { TokenStorageService } from '../storage/token-storage.service';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {

  const tokenStorage = inject(TokenStorageService);

  const token = tokenStorage.getToken();

  if (!token) {
    return next(req);
  }

  const authenticatedRequest = req.clone({

    setHeaders: {

      Authorization: `Bearer ${token}`

    }

  });

  return next(authenticatedRequest);

};
