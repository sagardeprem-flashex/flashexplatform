package com.flashex.triptrackingmicroservice.webservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.flashex.triptrackingmicroservice.lib.services.TrackingService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class DemoApplicationTest {

    @Autowired
    private TrackingService trackingService;

    @Test
    public void contextLoads() {
    }

}
