import { Injectable } from '@angular/core';
import { TokenStorageService } from './token-storage.service';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserguardService {

  token: any;
  constructor(private auth: TokenStorageService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    const currentUser = this.auth.getAuthorities();
    if (currentUser[0] === 'ROLE_USER' && this.auth.isTokenExpired()) {
      return true;
    }
    this.router.navigate(['/auth/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }}
