import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ILoginResponse } from '../interfaces/LoginResponse';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private http: HttpClient
  ) { }

  LOGIN_URL = 'https://flashex-dev.stackroute.io/login';

  login(userName: string, password: string): Observable<ILoginResponse> {
    return this.http.post<ILoginResponse>(this.LOGIN_URL, {
      userName,
      password
    });
  }
}
