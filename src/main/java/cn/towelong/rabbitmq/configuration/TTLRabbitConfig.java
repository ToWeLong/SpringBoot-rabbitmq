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
public class TTLRabbitConfig {
    // 声明队列
    @Bean
    public Queue ttlQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 1000 * 60);
        args.put("x-dead-letter-exchange","dead_exchange");
        args.put("x-dead-letter-routing-key","dead");
        return new Queue("ttl_queue", true, false, false, args);
    }


    @Bean
    public Queue ttlMsgQueue() {
        return new Queue("ttl_msg_queue", true);
    }

    // 声明交换机
    @Bean
    public DirectExchange ttlDirectExchange() {
        return new DirectExchange("ttl_exchange");
    }


    // 将交换机与队列通过routingKey绑定
    @Bean
    public Binding bindingTTLDirect() {
        return BindingBuilder.bind(ttlQueue())
                .to(ttlDirectExchange())
                .with("ttl");
    }

    @Bean
    public Binding bindingTTLMsgDirect() {
        return BindingBuilder.bind(ttlMsgQueue())
                .to(ttlDirectExchange())
                .with("ttlmsg");
    }

}
