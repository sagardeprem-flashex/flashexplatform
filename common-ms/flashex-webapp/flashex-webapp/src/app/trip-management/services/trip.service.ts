import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { IList } from 'src/app/delivery-executive/interfaces/trip-itinerary';

@Injectable({
  providedIn: 'root'
})
export class TripService {
  public location;

  constructor(private http: HttpClient) {
    this.load();

  }
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };


  private url = '../../../assets/tripsListFormat1.json';
  private dataSource = [];
  public behaviourSubject = new BehaviorSubject<IList[]>(this.dataSource);

  load() {
    this.http.get<IList[]>(this.url).subscribe(data => {
      this.dataSource = data;
      this.behaviourSubject.next(this.dataSource);
    });
  }
}
