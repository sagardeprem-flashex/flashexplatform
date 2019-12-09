import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
// import { IList } from '../interfaces/trip-itinerary';
import { IList } from '../interfaces/triplog';
// import { ITripProperties } from '../interfaces/trip-planning-properties';

@Injectable({
  providedIn: 'root'
})
export class TriplogService {

  constructor(private http: HttpClient) {
    this.load();
  }

  private url = 'triptracking-microservice-webservice/api/v1/triplogs';
  private updateUrl = 'triptracking-microservice-webservice/api/v1/updatelogs';

  private dataSource = [];
  private dataOne;
  public behaviourSubject = new BehaviorSubject<IList[]>(this.dataSource);

  load() {
    this.http.get<IList[]>(this.url).subscribe(
      data => {
        this.dataSource = data;
        this.behaviourSubject.next(this.dataSource);
      }
    );
  }

  // tslint:disable-next-line: ban-types
  getTripLog(id: string): Observable<Object> {
    return this.http.get('triptracking-microservice-webservice/api/v1/triplog?id=' + id);
  }

  // tslint:disable-next-line: ban-types
  updateTripLog(id: string, value: any): Observable<Object> {
    const options = {responseType: 'text' as 'json'};
    return this.http.put('triptracking-microservice-webservice/api/v1/updatelogs?id=' + id, value, options);
  }
}
