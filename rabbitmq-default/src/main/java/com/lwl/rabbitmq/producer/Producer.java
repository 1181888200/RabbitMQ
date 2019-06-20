package com.lwl.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	 * 发送消息 【默认模式，绑定的路由键（routing key）名称与队列名称相同】 DEFAULT_EXCHANGE
	 * @param queueName MQ queue名称
	 * @param message 发送的消息对象
	 * @author lwl 
	 * @create 2018年8月10日 下午2:45:25
	 */
    public void sendDefaule(String queueName,Object message){
        template.convertAndSend(queueName,message);
    }
	
	
}
