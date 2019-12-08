package com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {
        public String API_Key = "xyz";
        public static String[] addr = new String[]{};

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

}
