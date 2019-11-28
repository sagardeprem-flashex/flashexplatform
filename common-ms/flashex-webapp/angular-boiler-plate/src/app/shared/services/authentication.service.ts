import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthLoginInfo} from '../interfaces/logininfo';
import { SignUpInfo} from '../interfaces/signupinfo';
import { JwtResponse} from '../interfaces/jwtresponse';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private http: HttpClient
  ) { }

  // private loginUrl = 'http://localhost:8080/user-microservice/api/auth/signin';
  // private signupUrl = 'http://localhost:8080/user-microservice/api/auth/signup';
  private loginUrl = 'user-microservice/api/auth/signin';
  private signupUrl = 'user-microservice/api/auth/signup';



  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }
}
