package com.lwl.rabbitmq;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lwl.rabbitmq.RabbitmqDirectApplication;
import com.lwl.rabbitmq.producer.Producer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={RabbitmqDirectApplication.class})
@WebAppConfiguration
public class RabbitmqDirectApplicationTests {

	@Resource
	private Producer producer;
	
	@Resource
	private Producer producer2;
	
	@Resource
	private Producer producer3;
	
	@Resource
	private Producer producer4;
	
	/**
	 * 发送消息（此时把DirectConsumer,DirectConsumer2 ，DirectConsumer3, DirectConsumer4  注释掉）
	 * @author lwl
	 * @create 2019年6月20日 下午2:16:54
	 */
	@Test
	public void sendMessage() {
		String message = "我是Direct 发送的消息33333333";
		producer.sendDirect(message);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
	}
	
	/**
	 * 接收消息（此时把DirectConsumer, DirectConsumer4 打开）
	 * @author lwl
	 * @create 2019年6月20日 下午2:16:54
	 */
	@Test
	public void getMessage() {
		
	}
	
	/**
	 * 一个生产者，一个消费者（此时把DirectConsumer2 ，DirectConsumer3, DirectConsumer4  注释掉）
	 * @author lwl
	 * @create 2019年6月14日 下午1:04:10
	 */
	@Test
	public void send() {
		String message = "我是Direct 发送的消息22222";
		producer.sendDirect(message);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
	}

	/**
	 * 多个生成者，一个消费者（此时把DirectConsumer2 ，DirectConsumer3, DirectConsumer4 注释掉）
	 * @author lwl
	 * @create 2019年6月14日 下午1:14:13
	 */
	@Test
	public void send2() {
		int i = 0;
		String message = "我是Direct 发送的消息"+i;
		producer.sendDirect(message+i++);
		producer2.sendDirect(message+i++);
		producer3.sendDirect(message+i++);
		producer4.sendDirect(message+i++);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
	}

	/**
	 * 一个生产者，多个消费者（此时把DirectConsumer2 ，DirectConsumer3, DirectConsumer4  打开）
	 * 	结果： 此时只会被一个消费者消费掉
	 * @author lwl
	 * @create 2019年6月14日 下午1:15:48
	 */
	@Test
	public void send3() {
		String message = "我是Direct 发送的消息22222";
		producer.sendDirect(message);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
	}

	/**
	 * 多个生产者，多个消费者（此时把DirectConsumer2 ，DirectConsumer3, DirectConsumer4  打开）
	 * 	结果： 每个消费者有可能消费一个
	 * @author lwl
	 * @create 2019年6月14日 下午1:15:48
	 */
	@Test
	public void send4() {
		int i = 0;
		String message = "我是Direct 发送的消息"+i;
		producer.sendDirect(message+i++);
		producer2.sendDirect(message+i++);
		producer3.sendDirect(message+i++);
		producer4.sendDirect(message+i++);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
	}

}
