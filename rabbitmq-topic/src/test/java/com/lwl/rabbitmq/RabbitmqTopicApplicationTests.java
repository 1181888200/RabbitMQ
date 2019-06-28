package com.lwl.rabbitmq;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lwl.rabbitmq.producer.Producer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={RabbitmqTopicApplication.class})
@WebAppConfiguration
public class RabbitmqTopicApplicationTests {

	@Resource
	private Producer producer;
	
	/**
	 * 结果分析
	 * 	-----------------------客户端  3  收到数据 -----------------------
		topic_queue --> Receiver3  : topic send message 
		
		
		-----------------------客户端  1  收到数据 -----------------------
		topic_queue_2 --> Receiver1  : topic send message 
		
		由于调用的方法的路由routingkey是：topic.queue.key,
		那么绑定主题交换机对应的queue有2个， 一个是topic_queue （完全匹配），另一个是topic_queue_2（他的路由key是：topic.*.#）模糊匹配上了
		
	 * @author lwl
	 * @create 2019年6月14日 下午4:10:01
	 */
	@Test
	public void send() {
		String message = "topic send message ";
		producer.send(message);
	}

	
	/**
	 * 结果分析：
	 * 
	 * -----------------------客户端  1  收到数据 -----------------------
		topic_queue_2 --> Receiver1  : topic send message 2222222222
		
		由于发送的路由key是：topic.queue.two，不能匹配到topic.queue.key，所以队列topic_queue收不到
		而topic_queue_2（他的路由key是：topic.*.#）模糊匹配上了
		
	 * @author lwl
	 * @create 2019年6月14日 下午4:14:34
	 */
	@Test
	public void send2() {
		String message = "topic send message 2222222222";
		String routingKey = "topic.queue.two";
		producer.send(routingKey , message);
	}

}
