//package com.flashex.ordermicroservice.controllers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.flashex.shipmentmicroservice.lib.model.TestClass;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.flashex.ordermicroservice.messaging.Producer;
//@RestController
//@RequestMapping(value = "/kafka")
//@SpringBootApplication(scanBasePackages = "com.flashex.ordermicroservice")
//public class SampleController {
//    private final Producer producer;
//
//    @Autowired
//    public SampleController(Producer producer) {
//        this.producer = producer;
//    }
//
//    @PostMapping(value = "/publishJSON")
//    public void sendMessageToKafkaTopicJSON(@RequestBody TestClass message) throws JsonProcessingException {
//        this.producer.sendMessage(message);
//    }
//}
