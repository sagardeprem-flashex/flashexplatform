import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { ITripProperties } from '../interfaces/trip-planning-properties';
import { IItinerary } from '../interfaces/trip-itinerary';
import { IVehicle } from '../interfaces/vehicle';

@Injectable({
  providedIn: 'root'
})
export class TripItineraryService {

  public location;
  public planningProperties: ITripProperties;
  public selectedAlgo;

  constructor(private http: HttpClient) {
    this.load();
   }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  private tripItineraryUrl = '../../../assets/tripsListFormat2.json';

  private vehiclesListUrl = 'http://localhost:80/vehicles';

  private dataSource = [];
  public behaviourSubject = new BehaviorSubject<IItinerary[]>(this.dataSource);
  private vehiclesData = [];
  public vehicleBehaviourSubject = new BehaviorSubject<IVehicle[]>(this.vehiclesData);

  load() {
    this.http.get<IItinerary[]>(this.tripItineraryUrl).subscribe(data => {
      this.dataSource = data;
      this.behaviourSubject.next(this.dataSource);
    });
    this.http.get<IVehicle[]>(this.vehiclesListUrl).subscribe(data => {
      this.vehiclesData = data;
      this.vehicleBehaviourSubject.next(this.vehiclesData);
    });
  }

}
