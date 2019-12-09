export interface IPacket {
  packetId: string;
  productId: string;
  packetDescription: string;
  deliveryDescription: string;
  packetType: string;
  priority: string;
  currentStatus: string;
<<<<<<< HEAD
   statusList: IStatus[];
=======
  statusList: IStatus[];
>>>>>>> aa093d76455092c8f5664cf0d771a576a4f6b16a
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
<<<<<<< HEAD
  // statusList: [{
  //   statusValue: string;
  //   timeStamp: Date;
  // }];
=======
>>>>>>> aa093d76455092c8f5664cf0d771a576a4f6b16a
}

export interface IStatus {
statusValue: string;
timeStamp: Date;
}


