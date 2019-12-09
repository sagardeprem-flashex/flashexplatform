import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { ITripProperties } from '../interfaces/trip-planning-properties';
import { IItinerary } from '../interfaces/trip-itinerary';
import { IVehicle } from '../interfaces/vehicle';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TripItineraryService {

  public location;
  public planningProperties: ITripProperties;
  public selectedAlgo;
  public handleError = [];

  constructor(private http: HttpClient) {
    this.load();
   }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  // private tripItineraryUrl = '../../../assets/tripsListFormat2.json';

  private tripItineraryUrl = 'tripplanning-microservice-webservice/api/v1/tripitinerary';

  private vehiclesListUrl = 'http://localhost:80/vehicles';

  // private optimizationPropertiesUrl = 'http://gateway:8080/tripplanning-microservice-webservice/api/v1/optprops';

  private optimizationPropertiesUrl = 'tripplanning-microservice-webservice/api/v1/optprops';

  private dataSource = [];
  public behaviourSubject = new BehaviorSubject<IItinerary[]>(this.dataSource);
  private vehiclesData = [];
  public vehicleBehaviourSubject = new BehaviorSubject<IVehicle[]>(this.vehiclesData);

  load() {
    this.http.get<IItinerary[]>(this.tripItineraryUrl).subscribe(data => {
      this.dataSource = data;
      this.behaviourSubject.next(this.dataSource);
    },
    error => {
      this.handleError[1] = error;
    });
    this.http.get<IVehicle[]>(this.vehiclesListUrl).subscribe(data => {
      this.vehiclesData = data;
      this.vehicleBehaviourSubject.next(this.vehiclesData);
    },
    error => {
      this.handleError[2] = error;
    });

    this.http.get<ITripProperties>(this.optimizationPropertiesUrl + '/1793840').subscribe(data => {
      this.planningProperties = data;
      this.planningProperties.propertiesId = '1793840';
    },
    error => {
      this.handleError[3] = error;
    });
  }

  updateOptimizationProperties(properties: ITripProperties) {
    this.http.put<ITripProperties>( this.optimizationPropertiesUrl + '/' + properties.propertiesId,
                                    properties,
                                    this.httpOptions).pipe(
                                      catchError(this.handleError[0])
                                    );
  }
}
