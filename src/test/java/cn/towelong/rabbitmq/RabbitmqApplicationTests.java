package cn.towelong.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
class RabbitmqApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void directMQ() {
        // 并不会重复消费消息
        rabbitTemplate.convertAndSend("direct_exchange",
                "hello_direct_key",
                "通过Direct发送消息~");
    }

    @Test
    void workMQ() {
        // work抢夺分发的任务
        for (int i = 0; i < 7; i++) {
            rabbitTemplate.convertAndSend("work_queue", "哈哈哈" + i);
        }
    }

    // fanout广播模式
    @Test
    void fanoutMQ() {
        rabbitTemplate.convertAndSend("fanout_exchange","", "用户注册了~");
    }

    @Test
    void TopicMQ() {
        // #.sms.# --> SMS
        // *.email.# --> Email
        // # 表示匹配一个或多个
        // * 表示前面必须匹配一个
        String routingKey = "sms.email";
        rabbitTemplate.convertAndSend("topic_exchange",routingKey, "用户注册了~");
    }

    @Test
    void TTLMQ() {
        // 设置队列过期, 特点：过期的消息可以存起来
        String routingKey = "ttl";
        rabbitTemplate.convertAndSend("ttl_exchange",routingKey, "此消息会过期~");
    }

    @Test
    void TTLMsgMQ() {
        String routingKey = "ttlmsg";
        // 设置消息过期, 特点：过期的消息直接被丢弃
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };
        rabbitTemplate.convertAndSend("ttl_exchange",routingKey, "此消息会过期~", messagePostProcessor);
    }

    @Test
    void DelayMQ() {
        String routingKey = "lazy";
        rabbitTemplate.convertAndSend("lazy_exchange",routingKey, "延时一分钟到达");
        System.out.println(new Date().toString());
    }

}
