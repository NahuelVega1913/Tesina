import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const roleGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const requiredRoles = route.data?.['role'] as string[];
  const userRole = localStorage.getItem('role');

  if (requiredRoles?.includes(userRole ?? '')) {
    return true;
  } else {
    return router.createUrlTree(['/unauthorized']);
  }
};
