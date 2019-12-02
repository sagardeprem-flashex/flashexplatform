package com.flashex.shipmentmicroservice.lib.model;

import com.datastax.driver.core.DataType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("shipmentConfig")
public class ShipmentGenerationConfig {

    @PrimaryKeyColumn(name = "configid",  ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = DataType.Name.TEXT)
    private String configId;
    @CassandraType(type = DataType.Name.TIMESTAMP)
    private Date configDate;
    @CassandraType(type = DataType.Name.TEXT)
    private String active;
    @CassandraType(type = DataType.Name.TIMESTAMP)
    private Date effectiveDate;
    @CassandraType(type = DataType.Name.LIST, typeArguments = { DataType.Name.TEXT } )
    private List<String> groupStrategy;
    @CassandraType(type = DataType.Name.TEXT)
    private String sortBy;
}
