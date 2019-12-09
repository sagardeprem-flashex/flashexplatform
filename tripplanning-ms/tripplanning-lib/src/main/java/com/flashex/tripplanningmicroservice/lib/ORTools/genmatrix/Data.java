package com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix;

import com.flashex.tripplanningmicroservice.lib.model.Packet;
import com.flashex.tripplanningmicroservice.lib.model.Shipment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {
        public String API_Key = "xyz";
        public static String[] addr = new String[]{};

        private Shipment shipment;

//        private long[] demands;

        public ArrayList<Packet> packets;

        public String getAPI_Key() {
                return API_Key;
        }

        public static String[] getAddr() {
                return addr;
        }

        public void setAPI_Key(String API_Key) {
                this.API_Key = API_Key;
        }

        public static void setAddr(String[] addr) {
                Data.addr = addr;
        }

        public long[] createDemandArray(Shipment shipment) {

                long[] demandArray = new long[shipment.getPacketList().size()+1];
                for (int i = 1; i<=shipment.getPacketList().size(); i++) {
                        demandArray[i] = (long) (shipment.getPacketList().get(i).getHeight() * shipment.getPacketList().get(i).getBreadth() * shipment.getPacketList().get(i).getLength());
                }
                demandArray[0] = 0;
                return demandArray;
        }
}
