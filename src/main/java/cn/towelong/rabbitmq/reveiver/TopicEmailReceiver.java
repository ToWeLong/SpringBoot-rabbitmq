package cn.towelong.rabbitmq.reveiver;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "email.topic.queue", durable = "true", autoDelete = "false"),
        exchange = @Exchange(value = "topic_exchange", type = ExchangeTypes.TOPIC),
        key = "*.email.#"
))
public class TopicEmailReceiver {
    @RabbitHandler
    public void emailTopicReceiver(String msg) {
        System.out.println("email.topic.queue: " + msg);
    }
}
