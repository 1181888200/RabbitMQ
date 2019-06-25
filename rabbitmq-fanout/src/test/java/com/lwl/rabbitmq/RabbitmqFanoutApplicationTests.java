package com.lwl.rabbitmq;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lwl.rabbitmq.producer.Producer;
import com.lwl.rabbitmq.producer.Producer2;
import com.lwl.rabbitmq.producer.Producer3;
import com.lwl.rabbitmq.producer.Producer4;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={RabbitmqFanoutApplication.class})
@WebAppConfiguration
public class RabbitmqFanoutApplicationTests {

	
	@Resource
	private Producer producer;
	
	@Resource
	private Producer2 producer2;
	
	@Resource
	private Producer3 producer3;
	
	@Resource
	private Producer4 producer4;
	
	/**
	 * 发送消息（此时把FanoutConsumer,FanoutConsumer2 ，FanoutConsumer3, FanoutConsumer4  注释掉）
	 * @author lwl
	 * @create 2019年6月14日 下午1:04:10
	 */
	@Test
	public void sendMessage() {
		String message = "我是Fanout 发送的消息22222";
		producer.send(message);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
	}
	/**
	 * 接受消息（此时把FanoutConsumer,FanoutConsumer2 ，FanoutConsumer3, FanoutConsumer4  打开）
	 * @author lwl
	 * @create 2019年6月14日 下午1:04:10
	 */
	@Test
	public void getMessage() {
		System.out.println("-------------------------------接受数据--------------------------------");
	}
	
	/**
	 * 一个生产者，一个消费者（此时把FanoutConsumer2 ，FanoutConsumer3, FanoutConsumer4  注释掉）
	 * @author lwl
	 * @create 2019年6月14日 下午1:04:10
	 */
	@Test
	public void send() {
		String message = "我是Fanout 发送的消息22222";
		producer.send(message);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
	}

	/**
	 * 多个生成者，一个消费者（此时把FanoutConsumer2 ，FanoutConsumer3, FanoutConsumer4 注释掉）
	 * @author lwl
	 * @create 2019年6月14日 下午1:14:13
	 */
	@Test
	public void send2() {
		int i = 0;
		String message = "我是Fanout 发送的消息"+i;
		producer.send(message+i++);
		producer2.send(message+i++);
		producer3.send(message+i++);
		producer4.send(message+i++);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
	}

	/**
	 * 一个生产者，多个消费者（此时把FanoutConsumer2 ，FanoutConsumer3, FanoutConsumer4  打开）
	 * 	结果： 此时每个消费者都会接收到消息
	 * @author lwl
	 * @create 2019年6月14日 下午1:15:48
	 */
	@Test
	public void send3() {
		String message = "我是Fanout 发送的消息22222";
		producer.send(message);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
	}

	/**
	 * 多个生产者，多个消费者（此时把FanoutConsumer2 ，FanoutConsumer3, FanoutConsumer4  打开）
	 * 	结果： 每个消费者有可能消费一个
	 * @author lwl
	 * @create 2019年6月14日 下午1:15:48
	 */
	@Test
	public void send4() {
		int i = 0;
		String message = "我是Fanout 发送的消息"+i;
		producer.send(message+i++);
		producer2.send(message+i++);
		producer3.send(message+i++);
		producer4.send(message+i++);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
	}


}
