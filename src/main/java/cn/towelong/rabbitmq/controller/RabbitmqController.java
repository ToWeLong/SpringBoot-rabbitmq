package cn.towelong.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/direct")
    public String getDirectMsg() {
        // work抢夺分发的任务
        for (int i = 0; i < 7; i++) {
            rabbitTemplate.convertAndSend("work_queue", "哈哈哈" + i);
        }
        return "ok";
    }
}
