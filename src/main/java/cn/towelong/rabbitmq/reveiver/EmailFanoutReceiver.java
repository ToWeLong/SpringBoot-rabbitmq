package cn.towelong.rabbitmq.reveiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"email_fanout_queue"})
public class EmailFanoutReceiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("email_fanout_queue: "+ msg);
    }
}
