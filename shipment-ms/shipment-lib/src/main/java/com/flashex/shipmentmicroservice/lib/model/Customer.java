package com.flashex.shipmentmicroservice.lib.model;


import com.datastax.driver.core.DataType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

/*
* The following class stores the information of the customer
* */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@UserDefinedType("customer")
    public class Customer {

        /** String variables **/
        @CassandraType(type = DataType.Name.TEXT)
        public String firstName;
        @CassandraType(type = DataType.Name.TEXT)
        public String middleName;
        @CassandraType(type = DataType.Name.TEXT)
        public String lastName;
        @CassandraType(type = DataType.Name.TEXT)
        public String emailId;

        /** Integer variables **/
        @CassandraType(type = DataType.Name.INT)
        public int phoneNumber;

}
