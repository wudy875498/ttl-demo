package com.wudy.rabbitmq.ttl.demo.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;

/**
 * ClassName TtlMessageListener
 * Description 过期队列监听器，如果启用的话，消息就算是被消费了，除非在channel中应答模式为reject，并且不再重新投回，否则不会转发到死信队列
 *
 * @Author wudy
 * @Date 2019/6/4 10:34
 * @Version 1.0
 **/
@Slf4j
//@Service
@RabbitListener(queues = "X-Queue-ttl")
public class TtlMessageListener {

    @RabbitHandler
    public void receive(@Payload String message,Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        log.info("接受到消息》》》》》》" + message);
        if ("reject".equals(message)) {
            //应答模式为拒绝，并且不再投回
            channel.basicReject(tag,false);
        }
    }
}
