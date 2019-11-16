package com.flashex.ordermicroservice.workerservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashex.ordermicroservice.lib.model.TestClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {
        logger.info(String.format("$$ -> Consumed Message -> %s",message));
        logger.info(String.format("$$ -> Consumed Message -> %s",new ObjectMapper().readValue(message, TestClass.class)));
    }
}
