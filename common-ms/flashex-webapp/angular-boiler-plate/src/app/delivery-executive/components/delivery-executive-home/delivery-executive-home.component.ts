import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-delivery-executive-home',
  templateUrl: './delivery-executive-home.component.html',
  styleUrls: ['./delivery-executive-home.component.css']
})
export class DeliveryExecutiveHomeComponent implements OnInit {

  public board: string;
  public errorMessage: string;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getUserBoard().subscribe(
      data => {
        this.board = data;
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
  }

}
