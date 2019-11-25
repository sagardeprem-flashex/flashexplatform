import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from '../../services/token-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  private roles: string[];
  private authority: string;

  constructor(private router: Router, private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        } else if (role === 'ROLE_PM') {
          this.authority = 'pm';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
  }

  goToHome() {
    this.router.navigate(['in']);
  }
  logout() {
    this.tokenStorage.signOut();
    window.location.reload();
  }

}
