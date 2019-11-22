package com.flashex.shipmentmicroservice.lib.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {

    /** String variables **/

    public String shipmentId;

    /** Integer variables**/

    public int MAX_SHIPMENT_SIZE;
    /** Date variables **/

    public Date shipmentDate;

    /** Objects from local package **/
    public ArrayList<Package> orderList = new ArrayList<Package>();


}
