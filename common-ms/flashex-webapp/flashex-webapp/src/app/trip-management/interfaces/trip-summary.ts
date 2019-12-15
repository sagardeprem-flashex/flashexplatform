export interface Itripsummary {
  summaryId: string;
  summaryDate: Date;
  algorithms: string[];
  distanceSummary: number[];
  timeSummary: number[];
  costSummary: number[];
  droppedPacektsSummary: number[];
  nTrips: number[];
  volumeOccupancySummary: number[];
}
