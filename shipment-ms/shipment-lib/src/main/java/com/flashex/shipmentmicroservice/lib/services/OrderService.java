package com.flashex.shipmentmicroservice.lib.services;

import com.flashex.shipmentmicroservice.lib.model.Packet;
import com.flashex.shipmentmicroservice.lib.repository.OrderRepository;
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


    public List<Packet> getAllOrders()
    {
        return orderRepository.findAll();
    }

    public Packet saveOrder(Packet orderToBeSaved)
    {
        UUID orderUuid = UUID.randomUUID();
        UUID productUuid = UUID.randomUUID();
        orderToBeSaved.setOrderId(orderUuid);
        orderToBeSaved.setProductId(productUuid);
        return orderRepository.save(orderToBeSaved);
    }


}
