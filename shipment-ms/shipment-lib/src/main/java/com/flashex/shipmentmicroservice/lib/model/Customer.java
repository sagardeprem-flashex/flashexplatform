package com.flashex.shipmentmicroservice.lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* The following class stores the information of the customer
* */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    /** String variables **/

    public String firstName;
    public String middleName;
    public String lastName;
    public String emailId;

    /** Integer variables **/

    public int phoneNumber;
}
