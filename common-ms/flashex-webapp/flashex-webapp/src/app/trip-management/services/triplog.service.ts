import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ITripLog } from '../interfaces/triplog';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TriplogService {

  constructor(private http: HttpClient) {
    this.load();
   }

   private url = 'triptracking-microservice-webservice/api/v1/triplogs';
   private dataSource = [];
   public behaviourSubject = new BehaviorSubject<ITripLog[]>(this.dataSource);

   load() {
    this.http.get<ITripLog[]>(this.url).subscribe(
      data => {
        this.dataSource = data;
        this.behaviourSubject.next(this.dataSource);
      }
    );
   }
}
