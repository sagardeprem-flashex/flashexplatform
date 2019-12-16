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

  public date = '15-12-2019';
  constructor(private http: HttpClient) {
    this.loadSummary();
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  private tripSummaryUrl = 'tripplanning-microservice-webservice/api/v1/generate-summary';
  // private tripSummaryUrl = 'http://localhost:6900/summary'
  private dataSource = [];
  public behaviourSubject = new BehaviorSubject<Itripsummary[]>(this.dataSource);

  // get trip itinerary details

  loadSummary() {
    // this.http.get<Itripsummary[]>(this.tripSummaryUrl).subscribe(data => {
    this.http.get<Itripsummary[]>(this.tripSummaryUrl + '?date=' + this.date).subscribe(data => {
      // console.log(data);
      this.dataSource = data;
      this.behaviourSubject.next(this.dataSource);
    },
      error => {
        this.handleError[0] = error;
        // console.log(error);
      });

  }

  getSummary() {
    return this.dataSource[0];
  }
}
