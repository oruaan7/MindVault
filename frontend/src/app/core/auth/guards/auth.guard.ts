import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';

import { AuthStateService } from '../services/auth-state.service';

export const authGuard: CanActivateFn = () => {

  const authState = inject(AuthStateService);

  const router = inject(Router);

  if (authState.authenticated()) {

    return true;

  }

  return router.createUrlTree(['/login']);

};
