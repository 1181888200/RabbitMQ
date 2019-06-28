package com.lwl.rabbitmq.producer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

import com.lwl.rabbitmq.constant.Constants;

/**
 * 生成者
 * @author lwl
 * @create 2019年6月14日 上午10:56:41
 * @version 1.0
 */
@Component
public class AckProducer implements RabbitTemplate.ConfirmCallback , RabbitTemplate.ReturnCallback{

	@Autowired
    private RabbitTemplate rabbitTemplatenew;

    /**
     * 使用主题交换机
     * @param message
     * @author lwl
     * @create 2019年6月14日 上午10:54:54
     */
    public void send(Object message){
    	rabbitTemplatenew.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());  
        System.out.println();
        System.out.println("callbackSender UUID: " + correlationData.getId());  
        System.out.println();
        this.rabbitTemplatenew.convertAndSend(Constants.TOPIC_NAME,Constants.ROUTING_KEY, message, correlationData);  
    }
    /**
     * 
     * 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
     * @param correlationData
     * @param ack
     * @param cause
     * @author lwl
     * @create 2019年6月17日 上午9:46:59
     */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		//
		System.out.println();
		System.out.println();
		 System.out.println("callbakck confirm: " + correlationData.getId());
		 System.out.println();
		 System.out.println();
	}
	
	/**
	 * 通过实现 ReturnCallback 接口，启动消息失败返回，比如路由不到队列时触发回调
	 * @param message
	 * @param replyCode
	 * @param replyText
	 * @param exchange
	 * @param routingKey
	 * @author lwl
	 * @create 2019年6月17日 上午10:01:47
	 */
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("消息主体 message : "+message);
        System.out.println("消息主体 message : "+replyCode);
        System.out.println("描述："+replyText);
        System.out.println("消息使用的交换器 exchange : "+exchange);
        System.out.println("消息使用的路由键 routing : "+routingKey);		
	}
    
    
}
