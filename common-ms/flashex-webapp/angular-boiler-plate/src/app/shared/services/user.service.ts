import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userUrl = 'http://localhost:8080/user-microservice/api/test/user';
  private adminUrl = 'http://localhost:8080/user-microservice/api/test/admin';
  // private userUrl = 'http://flashex-dev.stackroute.io/api/test/user';
  // private adminUrl = 'http://flashex-dev.stackroute.io/api/test/admin';

  constructor(private http: HttpClient) { }

  getUserBoard(): Observable<string> {
    return this.http.get(this.userUrl, { responseType: 'text' });
  }

  getAdminBoard(): Observable<string> {
    return this.http.get(this.adminUrl, { responseType: 'text' });
  }
}
