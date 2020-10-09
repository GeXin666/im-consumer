package com.dxxt.im.ampq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class MessageSender {

    @Autowired
    private AmqpTemplate rabbitMq;

    @Autowired
    private ObjectMapper jsonMapper;

    public void sendMessage(Map<String, Object> message) throws JsonProcessingException {
        String jsonMessage = jsonMapper.writeValueAsString(message);
        if(log.isDebugEnabled()) {
            log.debug("send message to rabbitMQ: {} | msg: {}", AmpqConfig.messgaeQueue, jsonMessage);
        }

        rabbitMq.convertAndSend(AmpqConfig.exchangeName, AmpqConfig.messgaeQueue, jsonMessage);
    }
}
