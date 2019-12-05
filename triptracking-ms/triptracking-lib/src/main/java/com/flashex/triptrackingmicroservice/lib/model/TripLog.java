package com.flashex.triptrackingmicroservice.lib.model;

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
@NoArgsConstructor
@AllArgsConstructor
@Table("tripslog")
public class TripLog {
    @PrimaryKeyColumn(name = "tripItineraryId",  ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = DataType.Name.TEXT)
    private String tripItineraryId;

    @CassandraType(type = DataType.Name.TIMESTAMP)
    private Date tripStart;

    @CassandraType(type = DataType.Name.TIMESTAMP)
    private Date tripEnd;

//    private List<PacketLog> packetLogs;


}
