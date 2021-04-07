package cn.towelong.rabbitmq.reveiver;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "sms.topic.queue", durable = "true", autoDelete = "false"),
        exchange = @Exchange(value = "topic_exchange", type = ExchangeTypes.TOPIC),
        key = "#.sms.#"
))
public class TopicSmsReceiver {
    @RabbitHandler
    public void smsTopicReceiver(String msg) {
        System.out.println("sms.topic.queue: " + msg);
    }
}
