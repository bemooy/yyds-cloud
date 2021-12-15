package com.yyds.cloud.example.rabbitmq.produce;

import com.yyds.cloud.example.rabbitmq.config.MessageQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ActivityPeriodProduce implements  RabbitTemplate.ConfirmCallback ,RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public Object sendMessage(String message,Integer timeout){
        log.info("活动 {} 创建, 并在: {} 秒后结束", message, timeout);
        // 活动结束后,延迟5分钟
        Integer expiration = (timeout) * 1000;

        //设置回调对象
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setReturnCallback(this);

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setCorrelationId(UUID.randomUUID().toString());
        messageProperties.setHeader("x-delay", expiration);
        rabbitTemplate.convertAndSend(MessageQueue.EXCHANGE, MessageQueue.ROUTINGKEY1,
                new Message(message.getBytes(), messageProperties));
        return null;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean isSendSuccess, String s) {
        if (isSendSuccess) {
            log.info("confirm--message:消息发送成功: {} ", s);
        } else {
            log.error("confirm--message:消息发送失败: {}" + s);
        }

    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("return--message:" + new String(message.getBody()) + ",replyCode:" + replyCode
                + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);

    }
}
