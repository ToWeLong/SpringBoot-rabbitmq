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
public class LazyDeadRabbitConfig {

    @Bean
    public Queue LazyDeadQueue() {
        return new Queue("lazy_dead_queue", true);
    }

    @Bean
    public DirectExchange LazyDeadExchange() {
        return new DirectExchange("lazy_dead_exchange");
    }

    @Bean
    public Binding lazyDeadBinding() {
        return BindingBuilder.bind(LazyDeadQueue())
                .to(LazyDeadExchange())
                .with("lazy_dead");
    }
}
