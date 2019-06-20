package com.lwl.rabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
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

	/**
	 * 定义一个队列，queue
	 * @return
	 * @author lwl
	 * @create 2019年6月20日 下午2:04:36
	 */
    @Bean
    public Queue DirectQueue() {
    	return new Queue(Constants.DIRECT_QUEUE);
    }
    
    /**
     * 定义Direct 直连交换机
     * @return
     * @author lwl
     * @create 2019年6月20日 下午2:04:57
     */
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(Constants.DIRECT_NAME);
    }
    
    /**
     * 使用Direct 直连接，routekey 为 direct.queue.exchange
     * @param queueMessage
     * @param exchange
     * @return
     * @author lwl
     * @create 2019年6月14日 上午10:51:21
     */
    @Bean
    Binding bindingExchangeMessage(Queue DirectQueue, DirectExchange exchange) {
        return BindingBuilder.bind(DirectQueue).to(exchange).with(Constants.ROUTING_KEY);
    }
    
    /**
     * 定义第二个queue
     * @return
     * @author lwl
     * @create 2019年6月20日 下午2:05:36
     */
    @Bean
    public Queue DirectQueue2() {
    	return new Queue(Constants.DIRECT_QUEUE_TWO);
    }
    
    /**
     * 使用同样的路由key,将第二个queue绑定到同一个exchange上
     * @param DirectQueue2
     * @param exchange
     * @return
     * @author lwl
     * @create 2019年6月20日 下午2:05:52
     */
    @Bean
    Binding bindingExchangeMessage2(Queue DirectQueue2, DirectExchange exchange) {
        return BindingBuilder.bind(DirectQueue2).to(exchange).with(Constants.ROUTING_KEY);
    }
}
