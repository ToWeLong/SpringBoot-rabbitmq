package cn.towelong.rabbitmq.reveiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "lazy_dead_queue")
public class DelayReceiver {
    @RabbitHandler
    public void getDeadQueue(String msg) {
        System.out.println("延时消息: " + msg);
    }
}
