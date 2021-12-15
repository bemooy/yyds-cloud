package com.yyds.cloud.example.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.yyds.cloud.example.rabbitmq.config.MessageQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ActivityPeriodConsumer {


    @RabbitListener(queues = MessageQueue.ROUTINGKEY1)
    public void processMessage(String str, Channel channel, Message message) {
        try {
            // TODO 业务逻辑
        } catch (Exception e) {
            log.error("结束活动失败, id: {}", str, e);
        }
    }
}
