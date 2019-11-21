package com.flashex.shipmentmicroservice.lib.services;

import com.flashex.shipmentmicroservice.lib.model.Order;
import com.flashex.shipmentmicroservice.lib.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class OrderService {

//    @Autowired
    private OrderRepository orderRepository;

//    public OrderService(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }


    public List<Order> getAllOrders()
    {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order orderToBeSaved)
    {
        UUID orderUuid = UUID.randomUUID();
        UUID productUuid = UUID.randomUUID();
        orderToBeSaved.setOrderId(orderUuid);
        orderToBeSaved.setProductId(productUuid);
        return orderRepository.save(orderToBeSaved);
    }


}
