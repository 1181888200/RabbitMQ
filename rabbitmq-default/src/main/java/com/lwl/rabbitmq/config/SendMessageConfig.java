package com.lwl.rabbitmq.config;


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
	 * 这里我们只定义了一个queue对象，没有定义exchange
	 * @return
	 * @author lwl
	 * @create 2019年6月20日 下午1:06:34
	 */
    @Bean
    public Queue defaultQueue() {
        return new Queue(Constants.DEFALUT_QUEUE);
    }
    
    
}
