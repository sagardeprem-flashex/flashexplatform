package com.flashex.shipmentmicroservice.webservice.controller;


import com.flashex.shipmentmicroservice.lib.model.Order;
import com.flashex.shipmentmicroservice.lib.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/orders")
    public Order saveOrder(@RequestBody Order orderData)
    {
        Order savedOrder = orderService.saveOrder(orderData);
        return savedOrder;
    }
}
