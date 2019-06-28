package com.lwl.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lwl.rabbitmq.constant.Constants;

/**
 * 消费者 使用完全匹配模式
 * @author lwl
 * @create 2019年6月14日 上午10:57:11
 * @version 1.0
 */
@Component
@RabbitListener(queues = Constants.TOPIC_QUEUE)
public class TopicConsumer3 {

	@RabbitHandler
    public void process(String hello) {
		System.out.println();
		System.out.println("-----------------------客户端  3  收到数据 -----------------------");
        System.out.println(Constants.TOPIC_QUEUE+ " --> Receiver3  : " + hello);
        System.out.println();
    }
	
}
