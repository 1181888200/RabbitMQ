package com.lwl.rabbitmq.config;


import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
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
    public Queue headerQueue() {
    	return new Queue(Constants.HEADER_QUEUE);
    }
    
    @Bean
    public Queue headerQueue2() {
    	return new Queue(Constants.HEADER_QUEUE_TWO);
    }

    
    
    @Bean
    HeadersExchange exchange() {
        return new HeadersExchange(Constants.HEADER_NAME);
    }
    
    /**
     * 消息头订阅,消息发布前,为消息定义一个或多个键值对的消息头,然后消费者接收消息同时需要定义类似的键值对请求头:(如:x-mactch=all或者x_match=any)，
     * 只有请求头与消息头匹配,才能接收消息,忽略RoutingKey. 
     * 
     * 
     * 当"x-match"设置为“any”时，消息头的任意一个值被匹配就可以满足条件，
     * 而当"x-match"设置为“all”的时候，就需要消息头的所有值都匹配成功。
     */
     /**
      * 
      * @param headerQueue
      * @param exchange
      * @return
      * @author lwl
      * @create 2019年6月14日 下午4:37:41
      */
    @Bean
    Binding bindingExchangeMessage(Queue headerQueue, HeadersExchange  exchange) {
    	
    	Map<String, Object> headerValues = new HashMap<>();
    	headerValues.put("boot", "boot");
    	headerValues.put("hello", "hello");
    	headerValues.put("rabbit", "rabbit");
    	headerValues.put("mq", "mq");
    	headerValues.put("springboot", "springboot");
        return BindingBuilder.bind(headerQueue).to(exchange).whereAny(headerValues).match();
    }
    
    
   
    @Bean
    Binding bindingExchangeMessage2(Queue headerQueue2, HeadersExchange  exchange) {
    	Map<String, Object> headerValues = new HashMap<>();
    	headerValues.put("boot", "boot");
    	headerValues.put("hello", "hello");
    	headerValues.put("rabbit", "rabbit");
    	headerValues.put("mq", "mq");
        return BindingBuilder.bind(headerQueue2).to(exchange).whereAll(headerValues).match();
    }
    
    
}
