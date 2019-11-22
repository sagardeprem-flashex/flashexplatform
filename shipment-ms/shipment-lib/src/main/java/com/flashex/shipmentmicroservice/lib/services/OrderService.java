package com.flashex.shipmentmicroservice.lib.services;

import com.flashex.shipmentmicroservice.lib.model.Packet;
import com.flashex.shipmentmicroservice.lib.repository.PacketRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class OrderService {

//    @Autowired
    private PacketRepository orderRepository;

//    public OrderService(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

    public List<Packet> getAllOrders()
    {
        System.out.println("in service getAllOrders");
        List<Packet> orderList = new ArrayList<Packet>();
        System.out.println("kjnfkjewfekfekjfekj");
        if( orderRepository.findAll().size() != 0) {
            System.out.println("size is 0");
            orderRepository.findAll().forEach(orderList::add);
        }
        System.out.println("orderlist in service is "+ orderList);
        return orderList;
    }

    public Packet saveOrder(Packet orderToBeSaved)
    {
//        String orderUuid = UUID.randomUUID().toString();
//        String productUuid = UUID.randomUUID().toString();
//        orderToBeSaved.setOrderId(orderUuid);
//        orderToBeSaved.setProductId(productUuid);
        orderRepository.save(orderToBeSaved) ;
        return orderToBeSaved;
    }

}
