import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { ITripProperties } from '../interfaces/trip-planning-properties';
import { IItinerary } from '../interfaces/trip-itinerary';

@Injectable({
  providedIn: 'root'
})
export class TripItineraryService {

  public location;
  public planningProperties: ITripProperties;

  constructor(private http: HttpClient) {
    this.load();
   }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };


   private url = '../../../assets/tripsListFormat2.json';
   private dataSource = [];
   public behaviourSubject = new BehaviorSubject<IItinerary[]>(this.dataSource);

   load() {
     this.http.get<IItinerary[]>(this.url).subscribe(data => {
       this.dataSource = data;
       this.behaviourSubject.next(this.dataSource);
     });
   }

}
