import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router
} from '@angular/router';

import { TokenStorageService } from '../../shared/services/token-storage.service';
@Injectable({
  providedIn: 'root'
})
export class AuthguardService implements CanActivate {

  constructor(private auth: TokenStorageService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    const currentUser = this.auth.getAuthorities();
    if (currentUser[0] === 'ROLE_ADMIN') {
      return true;
    }
    this.router.navigate(['/auth/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }
}
