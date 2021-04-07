package cn.towelong.rabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列： 1. 过期的消息
 *          2. 超过队列消息的最大长度
 *          3. 消息被拒绝
 */
@Configuration
public class DeadRabbitConfig {
    // 声明队列
    @Bean
    public Queue deadQueue() {
        return new Queue("dead_queue", true);
    }

    // 声明交换机
    @Bean
    public DirectExchange deadDirectExchange() {
        return new DirectExchange("dead_exchange");
    }

    // 将交换机与队列通过routingKey绑定
    @Bean
    public Binding bindingTTLDeadDirect() {
        return BindingBuilder.bind(deadQueue())
                .to(deadDirectExchange())
                .with("dead");
    }
}
