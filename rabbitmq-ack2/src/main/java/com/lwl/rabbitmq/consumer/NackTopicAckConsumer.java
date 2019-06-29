package com.lwl.rabbitmq.consumer;

import java.io.IOException;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.amqp.support.AmqpHeaders;

import com.lwl.rabbitmq.constant.Constants;
import com.rabbitmq.client.Channel;

/**
 * 消费者
 * @author lwl
 * @create 2019年6月14日 上午10:57:11
 * @version 1.0
 */
@Component
@RabbitListener(queues = Constants.TOPIC_QUEUE)
public class NackTopicAckConsumer {

	
	/**
	 * 消费者获取消息时检查到头部包含 error 则 nack 消息
	 * @param message
	 * @param channel
	 * @param map
	 * @author lwl
	 * @create 2019年6月17日 上午10:52:54
	 */
	@RabbitHandler
	public void processMessage2(String message, Channel channel,@Headers Map<String,Object> map) {
		System.out.println("-----------------------客户端  nack  收到数据 -----------------------");
	    System.out.println(message);
	    if (map.get("error")!= null){
	        System.out.println("错误的消息");
	        try {
	        	//此时控制台重复打印，说明该消息被 nack 后一直重新入队列然后一直重新消费
	            channel.basicNack((Long)map.get(AmqpHeaders.DELIVERY_TAG),false,true);      //否认消息
	            
	            //也可以拒绝该消息，消息会被丢弃，不会重回队列
//	            channel.basicReject((Long)map.get(AmqpHeaders.DELIVERY_TAG),false);        //拒绝消息

	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    try {
	        channel.basicAck((Long)map.get(AmqpHeaders.DELIVERY_TAG),false);            //确认消息
	        
//	        channel.basicNack((Long)map.get(AmqpHeaders.DELIVERY_TAG),false,true);      //否认消息
	        
//	        channel.basicReject((Long)map.get(AmqpHeaders.DELIVERY_TAG),false);        //拒绝消息
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
