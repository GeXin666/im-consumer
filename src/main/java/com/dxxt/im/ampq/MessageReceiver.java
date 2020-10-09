package com.dxxt.im.ampq;

import com.dxxt.im.util.JedisUtil;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
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

    @Autowired
    private AmqpTemplate rabbitMq;

    @RabbitHandler
    public void process(String message) {
        log.info("Receive rabbitmq message: {}", message);

        JavaType javaType = TypeFactory.defaultInstance().constructType(LinkedHashMap.class);
        try {
            LinkedHashMap<String, Object> dataMap = jsonMapper.readValue(message,javaType);
            String clientId = (String) dataMap.get("clientId");
            String strMsg = (String) dataMap.get("message");

            //去Redis查询routingKey
            String routingKey = JedisUtil.getString(clientId);
            //用户在线-转发消息
            if(routingKey != null) {
                rabbitMq.convertAndSend(AmpqConfig.exchangeName, routingKey, message);
            } else {
                log.info("用户不在线-存储离线消息 userId: {}  message: {}", clientId, strMsg);
            }
        } catch (Exception e) {
            log.error("Parse rabbitmq message error: {}" , message, e);
        }
    }
}
