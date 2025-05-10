import { CanActivateFn, Router } from '@angular/router';

export const roleGuard: CanActivateFn = (route, state) => {
 constructor( router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const allowedRoles = route.data['roles'] as string[];
    const userRole = localStorage.getItem('role');

    if (userRole && allowedRoles.includes(userRole)) {
      return true;
    }

    router.navigate(['/unauthorized']);
    return false;
  }
};
