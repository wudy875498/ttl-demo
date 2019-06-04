package com.wudy.rabbitmq.ttl.demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName TestController
 * Description 测试
 *
 * @Author wudy
 * @Date 2019/6/4 11:19
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "message")
    public String send(String message) {
        rabbitTemplate.convertAndSend("X-Exchange-ttl","ttl",message);
        return "success:"+message;
    }
 }
