import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/shared/services/token-storage.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  centered = false;
  disabled = false;
  unbounded = false;
  public adminName;

  radius: number;
  color: string;

  constructor(private token: TokenStorageService) { }

  ngOnInit() {
    this.adminName = this.token.getUsername();
  }

}
