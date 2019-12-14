export interface Itripsummary{
  summaryId: String;
  summaryDate: Date;
  algorithms:  String[];
  distanceSummary: number[];
  timeSummary: number[];
  costSummary: number[];
  droppedPacektsSummary: number[];
  nTrips: number[];
  volumeOccupancySummary: number[];


}