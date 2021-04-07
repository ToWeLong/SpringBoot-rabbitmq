package cn.towelong.rabbitmq.reveiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"sms_fanout_queue"})
public class SmsFanoutReceiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("sms_fanout_queue: "+ msg);
    }
}
