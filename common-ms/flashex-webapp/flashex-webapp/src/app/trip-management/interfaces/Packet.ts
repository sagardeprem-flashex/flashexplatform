export interface IPacket {
  packetId: string;
  productId: string;
  packetDescription: string;
  deliveryDescription: string;
  packetType: string;
  priority: string;
  currentStatus: string;
  statusList: IStatus[];
  weight: number;
  length: number;
  breadth: number;
  height: number;
  costOfPacket: number;
  receivedDate: string;
  estimatedDeliveryDate: Date;
  actualDeliveryDate: Date;
  deliveryAddress: {
      addressLine1: string,
      city: string,
      state: string,
      latitude: number,
      longitude: number,
      pincode: number
  };
  customer: {
      firstName: string,
      middleName: string,
      lastName: string,
      emailId: string,
      phoneNumber: number
  };
}

export interface IStatus {
statusValue: string;
timeStamp: Date;
}


