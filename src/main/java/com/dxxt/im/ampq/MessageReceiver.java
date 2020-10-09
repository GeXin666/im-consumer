package com.dxxt.im.ampq;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Slf4j
@Component
@RabbitListener(queues = AmpqConfig.messgaeQueue)
public class MessageReceiver {

    @Autowired
    private ObjectMapper jsonMapper;

    @RabbitHandler
    public void process(String message) {
        log.info("Receive rabbitmq message: {}", message);

        JavaType javaType = TypeFactory.defaultInstance().constructType(LinkedHashMap.class);
        try {
            LinkedHashMap<String, Object> dataMap = jsonMapper.readValue(message,javaType);
        } catch (Exception e) {
            log.error("Parse rabbitmq message error: {}" , message, e);
        }
    }
}
