package cn.towelong.rabbitmq.reveiver;

import org.apache.tomcat.jni.Time;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "work_queue")
public class WorkReceiver2 {
    @RabbitHandler
    public void getWorkQueue(String msg) {
        System.out.println("work2: " + msg);
    }
}
