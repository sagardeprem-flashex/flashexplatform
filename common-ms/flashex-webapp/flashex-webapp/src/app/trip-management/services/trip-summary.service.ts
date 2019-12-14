import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { Itripsummary } from '../interfaces/trip-summary';
import { catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class TripSummaryService {

  public handleError = [];

  constructor(private http: HttpClient) { 
    this.loadSummary();
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  private tripItineraryUrl = 'tripplanning-microservice-webservice/api/v1/summary';

  private dataSource = [];
  public behaviourSubject = new BehaviorSubject<Itripsummary[]>(this.dataSource);

    // get trip itinerary details

    loadSummary() {
      this.http.get<Itripsummary[]>(this.tripItineraryUrl).subscribe(data => {
        this.dataSource = data;
        this.behaviourSubject.next(this.dataSource);
      },
      error => {
        this.handleError[0] = error;
      });

}
}
