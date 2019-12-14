package com.flashex.tripplanningmicroservice.lib.model;


import lombok.*;
import org.springframework.data.cassandra.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("summary")
public class DaySummary {

    String summaryId;

    Date summaryDate;
}
