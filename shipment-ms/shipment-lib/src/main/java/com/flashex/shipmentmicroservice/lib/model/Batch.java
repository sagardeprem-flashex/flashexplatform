package com.flashex.shipmentmicroservice.lib.model;


import java.util.ArrayList;
import java.util.Date;

/*
 * The following class is responsible for creating Shipments of Orders based on sorting,
 * filtering, grouping and MAX_SHIPMENT_SIZE criteria
 *
 * Batch -1---has---*-->Shipments
 * Shipment--1--has---*-->Orders
 *
 * */


public class Batch {

    /** String variables **/

    public String batchId;

    /** Date variables **/

    public Date batchDate;

    /** Objects of same package**/

    public ArrayList<Shipment> shipmentList = new ArrayList<Shipment>();

}
