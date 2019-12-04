package com.flashex.tripplanningmicroservice.lib.repository;

import com.flashex.tripplanningmicroservice.lib.model.TripItinerary;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface TripItineraryRepository extends CassandraRepository<TripItinerary,String> {



    

}
