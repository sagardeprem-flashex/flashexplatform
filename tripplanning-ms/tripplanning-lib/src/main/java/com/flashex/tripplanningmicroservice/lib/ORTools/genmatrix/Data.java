package com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix;

import com.flashex.tripplanningmicroservice.lib.model.Shipment;
import com.flashex.tripplanningmicroservice.lib.model.TestClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Data {
        public String API_Key = "AIzaSyDWuoodBo_sLP8B1_wWVDwkyGwaavc3UUY";
        public String[] addr = new String[] {"hello"};


        public String getAPI_Key() {
                return API_Key;
        }

        public void setAPI_Key(String API_Key) {
                this.API_Key = API_Key;
        }

        public String[] getAddr() {
                return addr;
        }

        public void setAddr(String[] addr) {
                this.addr = addr;
        }

}
