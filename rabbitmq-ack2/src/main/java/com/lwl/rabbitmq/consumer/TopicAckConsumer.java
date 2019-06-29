package com.lwl.rabbitmq.consumer;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
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
public class TopicAckConsumer {

	
	@RabbitHandler
	public void processMessage2(String message,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag) {
		System.out.println("-----------------------客户端  1  收到数据 -----------------------");
		System.out.println(message);
		System.out.println();
	    try {
	    	/**
	    	 * 需要注意的 basicAck 方法需要传递两个参数
					deliveryTag（唯一标识 ID）：当一个消费者向 RabbitMQ 注册后，会建立起一个 Channel ，
						RabbitMQ 会用 basic.deliver 方法向消费者推送消息，这个方法携带了一个 delivery tag， 
							它代表了 RabbitMQ 向该 Channel 投递的这条消息的唯一标识 ID，是一个单调递增的正整数，delivery tag 的范围仅限于 Channel
					
					multiple：为了减少网络流量，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息

	    	 */
	        channel.basicAck(tag,false);            // 确认消息
	    	
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
