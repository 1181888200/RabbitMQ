package com.lwl.rabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
    public Queue topicQueue() {
    	return new Queue(Constants.TOPIC_QUEUE);
    }
    
    @Bean
    public Queue topicQueue2() {
    	return new Queue(Constants.TOPIC_QUEUE_TWO);
    }

    
    
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(Constants.TOPIC_NAME);
    }
    
    /**
     * 使用主题交换机， 
     *  	将队列Constants.TOPIC_QUEUE与exchange绑定，binding_key为topic.queue.key,就是完全匹配
     * @param topicQueue
     * @param exchange
     * @return
     * @author lwl
     * @create 2019年6月14日 上午10:51:21
     */
    @Bean
    Binding bindingExchangeMessage(Queue topicQueue, TopicExchange  exchange) {
        return BindingBuilder.bind(topicQueue).to(exchange).with(Constants.ROUTING_KEY);
    }
    
    
    /**
     * 使用主题交换机，routekey 
     * 	将队列Constants.TOPIC_QUEUE_TWO与exchange绑定，binding_key为topic.*.#,模糊匹配
     * @param topicQueue2
     * @param exchange
     * @return
     * @author lwl
     * @create 2019年6月14日 上午10:51:21
     */
    @Bean
    Binding bindingExchangeMessage2(Queue topicQueue2, TopicExchange  exchange) {
        return BindingBuilder.bind(topicQueue2).to(exchange).with(Constants.ROUTING_KEY_TWO);
    }
    
    
}
