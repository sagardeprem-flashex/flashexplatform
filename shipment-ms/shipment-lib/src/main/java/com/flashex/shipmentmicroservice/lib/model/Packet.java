package com.flashex.shipmentmicroservice.lib.model;

import com.datastax.driver.core.DataType;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("Packet")
public class Packet {

    /** String variables **/

    @PrimaryKey

    private String orderId;
    private String productId;
    private String orderDescription;
    private String deliveryDescription;
    private String orderType;
    private String priority;
    private String status;

    /** Float variables **/
    private float weight;
    private float length;
    private float breadth;
    private float height;
    private float costOfOrder;


    /** Date type variables**/
    private Date receivedDate;

    //to be updated by Trip Planning microservice
    private Date estimatedDeliveryDate;

    //to be updated by Trip Itinerary microservice
    private Date actualDeliveryDate;

    /** Enum type variables **/


    /** Objects of same package**/
//    private DeliveryAddress deliveryAddress;
//    private Customer customer;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getDeliveryDescription() {
        return deliveryDescription;
    }

    public void setDeliveryDescription(String deliveryDescription) {
        this.deliveryDescription = deliveryDescription;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getBreadth() {
        return breadth;
    }

    public void setBreadth(float breadth) {
        this.breadth = breadth;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public Date getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(Date actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public DeliveryAddress getDeliveryAddress() {
//        return deliveryAddress;
//    }
//
//    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
//        this.deliveryAddress = deliveryAddress;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }


    public float getCostOfOrder() {
        return costOfOrder;
    }

    public void setCostOfOrder(float costOfOrder) {
        this.costOfOrder = costOfOrder;
    }
}
