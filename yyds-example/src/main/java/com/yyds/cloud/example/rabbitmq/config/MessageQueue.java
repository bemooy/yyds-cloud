package com.yyds.cloud.example.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessageQueue {
    /**
     * 消息交换机的名字
     */
    public static final String EXCHANGE = "ACTIVITY_TIMEOUT_EXCHANGE";
    /**
     * 队列key1
     */
    public static final String ROUTINGKEY1 = "ACTIVITY_TIMEOUT_ROUTES";


    /**
     * 延时队列交换机
     * 注意这里的交换机类型：CustomExchange
     *
     * @return
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(EXCHANGE, "x-delayed-message", true, false, args);
    }

    /**
     * 延时队列
     *
     * @return
     */
    @Bean
    public Queue delayQueue() {
        return new Queue(ROUTINGKEY1, true);
    }

    /**
     * 给延时队列绑定交换机
     * @return
     */
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(ROUTINGKEY1).noargs();

    }
}