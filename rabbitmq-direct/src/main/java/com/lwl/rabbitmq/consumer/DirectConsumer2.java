package com.lwl.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lwl.rabbitmq.constant.Constants;

/**
 * 消费者
 * @author lwl
 * @create 2019年6月14日 上午10:57:11
 * @version 1.0
 */
@Component
@RabbitListener(queues = Constants.DIRECT_QUEUE)
public class DirectConsumer2 {

	@RabbitHandler
    public void process(String hello) {
		System.out.println();
		System.out.println("-----------------------客户端  2  收到数据 -----------------------");
        System.out.println(Constants.DIRECT_QUEUE+ " --> Receiver2  : " + hello);
        System.out.println();
    }
	
}
