package com.lwl.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lwl.rabbitmq.constant.Constants;

/**
 * 消费者 匹配任意一个
 * @author lwl
 * @create 2019年6月14日 上午10:57:11
 * @version 1.0
 */
@Component
public class HeaderConsumer {
	
	@RabbitListener(queues = Constants.HEADER_QUEUE)
	@RabbitHandler
    public void process( Message message) {
		System.out.println();
		System.out.println("-----------------------客户端  1  收到数据 -----------------------");
        System.out.println(Constants.HEADER_QUEUE+ " --> Receiver1  : " + message.toString());
        System.out.println();
    }
	
}
