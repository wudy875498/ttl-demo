package com.wudy.rabbitmq.ttl.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * ClassName DeadMessageListener
 * Description 死信队列监听器
 *
 * @Author wudy
 * @Date 2019/6/4 11:17
 * @Version 1.0
 **/
@Slf4j
@Service
@RabbitListener(queues = "X-Queue-dead")
public class DeadMessageListener {

    @RabbitHandler
    public void receive(@Payload String message) {
        log.info("接收到死信消息：" +message);
    }
}
