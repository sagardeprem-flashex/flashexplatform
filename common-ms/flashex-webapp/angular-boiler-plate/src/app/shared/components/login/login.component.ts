import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../../services/authentication.service';
import { LoginStateService } from '../../services/login-state.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private authenticationService: AuthenticationService,
    private loginStateService: LoginStateService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  login = new FormGroup({
    userName: new FormControl('', [Validators.minLength(8), Validators.required]),
    password: new FormControl('', [Validators.minLength(8), Validators.required])}
  );

  tryLogin(){
    console.log(this.login.value.userName);
    console.log(this.login.value.password);
    this.authenticationService.login(
      this.login.value.userName,
      this.login.value.password
    ).subscribe(
      response => {if (response.token){
        this.loginStateService.setToken(response.token);
        this.router.navigateByUrl('/home')
      }}
    )
  }
  // onSubmit() {
  //   // TODO: Use EventEmitter with form value
  //   console.warn(this.login.value);
  // }

}
