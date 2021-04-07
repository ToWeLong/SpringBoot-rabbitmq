/**
 * @作者 WeLong
 * @博客 $ https://towelong.cn
 * @开源项目 $ https://github.com/ToWeLong
 * @创建时间 2021/3/5 16:02
 */
package cn.towelong.rabbitmq.reveiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "direct_queue")
public class DirectReceiver1 {

    @RabbitHandler
    public void helloProcess(String msg) {
        System.out.println("DirectReceiver接收到了消息: " + msg);
    }
}
