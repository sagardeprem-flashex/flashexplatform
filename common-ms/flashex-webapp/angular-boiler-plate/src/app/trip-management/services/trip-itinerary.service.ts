import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { IList } from '../interfaces/trip-itinerary';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TripItineraryService {

  public location;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private http: HttpClient) {
    this.load();
   }

   private url = '../../../assets/tripItinerary.json';
   private dataSource = [];
   public behaviourSubject = new BehaviorSubject<IList[]>(this.dataSource);
   load() {
     this.http.get<IList[]>(this.url).subscribe(data => {
       this.dataSource = data;
       this.behaviourSubject.next(this.dataSource);
     });
   }
}
