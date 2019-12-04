package com.flashex.tripplanningmicroservice.lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacketList {

    private ArrayList<Packet> listOfPackets;

//    public static void setListOfPackets(ArrayList<Packet> packets) {
//        PacketList.listOfPackets = listOfPackets;
//    }
//
//    public static ArrayList<Packet> getListOfPackets(){
//        return listOfPackets;
//    }

}
