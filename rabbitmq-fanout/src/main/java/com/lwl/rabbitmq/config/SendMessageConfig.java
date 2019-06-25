package com.lwl.rabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lwl.rabbitmq.constant.Constants;

/**
 * 发送消息 配置发送消息的队列queue
 * @author lwl
 * @create 2018年8月10日 下午2:37:38
 * @version 1.0
 */
@Configuration
public class SendMessageConfig {

    @Bean
    public Queue fanoutQueue() {
    	return new Queue(Constants.FANOUT_QUEUE);
    }

    
    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(Constants.FANOUT_NAME);
    }
    
    /**
     * 使用扇型交换机，routekey 
     * @param queueMessage
     * @param exchange
     * @return
     * @author lwl
     * @create 2019年6月14日 上午10:51:21
     */
    @Bean
    Binding bindingExchangeMessage(Queue fanoutQueue, FanoutExchange  exchange) {
        return BindingBuilder.bind(fanoutQueue).to(exchange);
    }
    
    
    /**
     * 第二个队列
     * @return
     * @author lwl
     * @create 2019年6月14日 下午2:11:31
     */
    @Bean
    public Queue fanoutTwoQueue() {
    	return new Queue(Constants.FANOUT_QUEUE_TWO);
    }
    
    @Bean
    Binding bindingExchangeMessageTwo(Queue fanoutTwoQueue, FanoutExchange  exchange) {
    	return BindingBuilder.bind(fanoutTwoQueue).to(exchange);
    }
}
