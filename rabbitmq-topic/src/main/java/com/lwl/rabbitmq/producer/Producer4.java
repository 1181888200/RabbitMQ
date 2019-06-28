package com.lwl.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lwl.rabbitmq.constant.Constants;

/**
 * 生成者
 * @author lwl
 * @create 2019年6月14日 上午10:56:41
 * @version 1.0
 */
@Component
public class Producer4 {

	@Autowired
    private AmqpTemplate template;

    /**
     * 使用主题交换机
     * @param message
     * @author lwl
     * @create 2019年6月14日 上午10:54:54
     */
    public void send(Object message){
    	template.convertAndSend(Constants.TOPIC_NAME,Constants.ROUTING_KEY,message);
    }
    
    /**
     * 如果routingKey 推送到对应的queue中
     * @param routingKey
     * @param message
     * @author lwl
     * @create 2019年6月14日 下午2:56:39
     */
    public void send(String routingKey,Object message){
    	template.convertAndSend(Constants.TOPIC_NAME,routingKey,message);
    }
    
}
