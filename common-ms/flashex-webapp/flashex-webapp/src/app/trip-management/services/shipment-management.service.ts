import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { IPacket } from '../interfaces/Packet';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ShipmentManagementService {

  public packetList: IPacket;
  public errormsg;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor( private http: HttpClient) {
    this.load();

  }

  //  private url = 'http://localhost:6010/api/v1/packets';
 //  private url = 'http://gateway:8080/api/v1/packets';
  private url = '  http://localhost:3000/orderList';
  private dataSource = [];
  public behaviourSubject = new BehaviorSubject<IPacket[]>(this.dataSource);
  load() {
    this.http.get<IPacket[]>(this.url).subscribe(
      data => {
        this.dataSource = data;
        this.behaviourSubject.next(this.dataSource);
      },
      error => {
        this.errormsg = error.message;
      }
    );
  }
}
