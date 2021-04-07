package cn.towelong.rabbitmq.reveiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "dead_queue")
public class DeadReceiver {
    @RabbitHandler
    public void getDeadQueue(String msg) {
        System.out.println("dead: " + msg);
    }
}
