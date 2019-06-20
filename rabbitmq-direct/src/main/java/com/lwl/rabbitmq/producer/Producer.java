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
public class Producer {

	@Autowired
    private AmqpTemplate template;

    /**
     * 带有路由key的直连交换机
     * @param message
     * @author lwl
     * @create 2019年6月14日 上午10:54:54
     */
    public void sendDirect(Object message){
    	template.convertAndSend(Constants.DIRECT_NAME,Constants.ROUTING_KEY,message);
    }
    
	
}
