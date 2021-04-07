package cn.towelong.rabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class LazyRabbitConfig {

    @Bean
    public Queue LazyQueue() {
        Map<String, Object> args = new HashMap<>();
        // 消息过期时间
        args.put("x-message-ttl", 1000 * 60 * 2);
        // 过期后的消息投递到死信队列
        args.put("x-dead-letter-exchange","lazy_dead_exchange");
        args.put("x-dead-letter-routing-key","lazy_dead");
        return new Queue("lazy_queue", true, false, false, args);
    }

    @Bean
    public DirectExchange lazyExchange() {
        return new DirectExchange("lazy_exchange");
    }

    @Bean
    public Binding bindingLazyExchange() {
        return BindingBuilder.bind(LazyQueue())
                .to(lazyExchange())
                .with("lazy");
    }
}
