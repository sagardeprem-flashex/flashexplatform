export interface IItinerary {
  tripItineraryId: number;
  packets: IPackets[];
  plannedStartTime: Date;
  plannedEndTime: Date;
  plannedTotalDistance: number;
  vehicle: IVehicle;
  tripExpense: number;
  occupiedVolume: number;
}
