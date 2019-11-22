package com.flashex.shipmentmicroservice.webservice.controller;


import com.flashex.shipmentmicroservice.lib.model.Packet;
import com.flashex.shipmentmicroservice.lib.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@SpringBootApplication(scanBasePackages = "com.flashex.shipmentmicroservice")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<Packet> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/orders")
    public Packet saveOrder(@RequestBody Packet orderData)
    {
        Packet savedOrder = orderService.saveOrder(orderData);
        return savedOrder;
    }

    @GetMapping("/string")
    public String getString()
    {
        return "hi Sudhanshu";
    }

    @PostMapping("/string")
    public String getpostString(@RequestParam("message") String message){return message;}

}
