/**
 * @作者 WeLong
 * @博客 $ https://towelong.cn
 * @开源项目 $ https://github.com/ToWeLong
 * @创建时间 2021/3/5 15:58
 */
package cn.towelong.rabbitmq.configuration;

import org.omg.CosNaming.BindingListHelper;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    // 声明队列
    @Bean
    public Queue helloQueue() {
        return new Queue("direct_queue");
    }

    // 声明交换机
    @Bean
    public DirectExchange TestDirectExchange() {
        return new DirectExchange("direct_exchange");
    }

    // 将交换机与队列通过routingKey绑定
    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(helloQueue())
                .to(TestDirectExchange())
                .with("hello_direct_key");
    }
    // ====================

    // work队列
    @Bean
    public Queue workQueue() {
        return new Queue("work_queue");
    }
    // ====================

    // 发布/订阅 队列

    @Bean
    public Queue emailFanoutQueue() {
        return new Queue("email_fanout_queue");
    }

    @Bean
    public Queue SmsFanoutQueue() {
        return new Queue("sms_fanout_queue");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_exchange");
    }

    @Bean
    public Binding bindingEmailFanout() {
        return BindingBuilder.bind(SmsFanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingSmsFanout() {
        return BindingBuilder.bind(emailFanoutQueue()).to(fanoutExchange());
    }
    // ====================


}
