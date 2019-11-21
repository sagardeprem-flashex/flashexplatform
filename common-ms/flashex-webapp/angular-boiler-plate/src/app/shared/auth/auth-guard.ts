import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { LoginStateService } from '../services/login-state.service';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private loginStateService: LoginStateService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    // const redirectUrl = route['routerState']['url'];

    if (this.loginStateService.isLogged()) {
      return true;
    }

    this.router.navigateByUrl(
      this.router.createUrlTree(
        ['/login'], {
          queryParams: {
            // redirectUrl
          }
        }
      )
    );

    return false;

  }
}
