export interface IShipmentConfig {
  configId: string;
  configDate: Date;
  groupStrategy: string[];
  sortBy: string;
  maxShipmentSize: number;
  originAddress: {
    addressLine1: string,
    city: string,
    state: string,
    latitude: number,
    longitude: number,
    pincode: number
  }
}
