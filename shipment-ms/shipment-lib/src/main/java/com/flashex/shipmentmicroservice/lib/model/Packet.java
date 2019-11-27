package com.flashex.shipmentmicroservice.lib.model;

import com.datastax.driver.core.DataType;
import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;

@Table("packet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Packet {

    /** String variables **/

    @PrimaryKeyColumn(name = "packetId",  ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = DataType.Name.TEXT)
    private String packetId;

    @CassandraType(type = DataType.Name.TEXT)
    private String productId;
//    private String packetDescription;
//    private String deliveryDescription;
//    private String packetType;
//    private String priority;
//    private String status;
//
//    /** Float variables **/
//    private float weight;
//    private float length;
//    private float breadth;
//    private float height;
    @CassandraType(type = DataType.Name.FLOAT)
    private float costOfPacket;


    /** Date type variables**/
    @CassandraType(type = DataType.Name.TIMESTAMP)
    private Date receivedDate;
//
//    //to be updated by Trip Planning microservice
//    private Date estimatedDeliveryDate;
//
//    //to be updated by Trip Itinerary microservice
//    private Date actualDeliveryDate;
//
//    /** Enum type variables **/
//

    /** Objects of same package**/
    @CassandraType(type = DataType.Name.UDT, userTypeName = "deliveryAddress")
    private DeliveryAddress deliveryAddress;

    @CassandraType(type = DataType.Name.UDT, userTypeName = "customer")
    private Customer customer;


}
