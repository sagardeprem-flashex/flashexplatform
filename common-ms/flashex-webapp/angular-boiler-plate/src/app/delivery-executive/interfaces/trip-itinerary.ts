export interface IList {
  tripItineraryId: number;
  orders: [{
    orderId: number;
    deliveryAddress: string;
    deliveryLocation: number[];
  }];
}
