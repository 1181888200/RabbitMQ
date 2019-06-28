package com.lwl.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lwl.rabbitmq.producer.Producer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={RabbitmqHeaderApplication.class})
@WebAppConfiguration
public class RabbitmqHeaderApplicationTests {

	@Resource
	private Producer producer;
	
	
	/**
	 * 因为发布的头部里面包含的都满足2个queue ，所以2个queue都可以接收到
	 * 
	 * 出现错误：Caused by: org.springframework.amqp.AmqpException: No method found for class [B
		解决方法：	@RabbitListener(queues=queue_name)不需要放在类上。直接注释在方法上就好
	 * 
	 * @author lwl
	 * @create 2019年6月17日 上午8:52:22
	 */
	@Test
	public void send() {
		
		Map<String, Object> headerValues = new HashMap<>();
    	headerValues.put("boot", "boot");
    	headerValues.put("hello", "hello");
    	headerValues.put("rabbit", "rabbit");
    	headerValues.put("mq", "mq");
		
		/**
		 * 声明消息 (消息体, 消息属性)
		 */
		MessageProperties messageProperties = new MessageProperties();
		// 设置消息是否持久化。Persistent表示持久化，Non-persistent表示不持久化
		messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
		messageProperties.setContentType("UTF-8");
		messageProperties.getHeaders().putAll(headerValues);
		
		Message message = new Message("hello,rabbit_headers_any！".getBytes(), messageProperties);
		System.out.println(message.toString());
		producer.send(message);
		
		System.out.println("");
		System.out.println("---------------------------数据发送成功-------------------------");
		System.out.println("");
		
	}

	/***
	 * 虽然2个queue的头部都包含这个headerValues， 但是HEADER_QUEUE_TWO是完全匹配，而HEADER_QUEUE是模糊匹配，所以只有HEADER_QUEUE能接收到数据
	 * @author lwl
	 * @create 2019年6月17日 上午8:54:28
	 */
	@Test
	public void send2() {
		Map<String, Object> headerValues = new HashMap<>();
    	headerValues.put("boot", "boot");
    	headerValues.put("hello", "hello");
    	headerValues.put("rabbit", "rabbit");
//    	headerValues.put("mq", "mq");
//    	headerValues.put("springboot", "springboot");
		
		/**
		 * 声明消息 (消息体, 消息属性)
		 */
		MessageProperties messageProperties = new MessageProperties();
		// 设置消息是否持久化。Persistent表示持久化，Non-persistent表示不持久化
		messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
		messageProperties.setContentType("UTF-8");
		messageProperties.getHeaders().putAll(headerValues);
		
		Message message = new Message("hello,rabbit_headers_any！".getBytes(), messageProperties);
		System.out.println(message.toString());
		producer.send(message);
		
		System.out.println("");
		System.out.println("---------------------------数据发送成功-------------------------");
		System.out.println("");
	}
}
