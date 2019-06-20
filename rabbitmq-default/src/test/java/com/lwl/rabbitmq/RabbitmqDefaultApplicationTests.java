package com.lwl.rabbitmq;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lwl.rabbitmq.constant.Constants;
import com.lwl.rabbitmq.producer.Producer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={RabbitmqDefaultApplication.class})
@WebAppConfiguration
public class RabbitmqDefaultApplicationTests {

	@Resource
	private Producer producer;
	
	@Resource
	private Producer producer2;
	
	@Resource
	private Producer producer3;
	
	@Resource
	private Producer producer4;
	
	/**
	 * 将消息发送到消息队列中（此时把DefaultConsumer,DefaultConsumer2 ，DefaultConsumer3, DefaultConsumer4  注释掉）
	 * @author lwl
	 * @create 2019年6月14日 下午1:04:10
	 */
	@Test
	public void sendProducer() {
		String message = "我是default 发送的消息22222";
		producer.sendDefaule(Constants.DEFALUT_QUEUE, message);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
		System.out.println();
	}
	
	/**
	 * 把DefaultConsumer 注释去掉即可
	 * @author lwl
	 * @create 2019年6月20日 下午1:22:24
	 */
	@Test
	public void getMessage() {
		
	}
	
	
	
	/**
	 * 一个生产者，一个消费者（此时把DefaultConsumer2 ，DefaultConsumer3, DefaultConsumer4  注释掉）
	 * @author lwl
	 * @create 2019年6月14日 下午1:04:10
	 */
	@Test
	public void send() {
		String message = "我是default 发送的消息22222";
		producer.sendDefaule(Constants.DEFALUT_QUEUE, message);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
		System.out.println();
	}

	/**
	 * 多个生成者，一个消费者（此时把DefaultConsumer2 ，DefaultConsumer3, DefaultConsumer4 注释掉）
	 * @author lwl
	 * @create 2019年6月14日 下午1:14:13
	 */
	@Test
	public void send2() {
		int i = 0;
		String message = "我是default 发送的消息"+i;
		producer.sendDefaule(Constants.DEFALUT_QUEUE, message+i++);
		producer2.sendDefaule(Constants.DEFALUT_QUEUE, message+i++);
		producer3.sendDefaule(Constants.DEFALUT_QUEUE, message+i++);
		producer4.sendDefaule(Constants.DEFALUT_QUEUE, message+i++);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
		System.out.println();
	}

	/**
	 * 一个生产者，多个消费者（此时把DefaultConsumer2 ，DefaultConsumer3, DefaultConsumer4  打开）
	 * 	结果： 此时只会被一个消费者消费掉
	 * @author lwl
	 * @create 2019年6月14日 下午1:15:48
	 */
	@Test
	public void send3() {
		String message = "我是default 发送的消息22222";
		producer.sendDefaule(Constants.DEFALUT_QUEUE, message);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
		System.out.println();
	}

	/**
	 * 多个生产者，多个消费者（此时把DefaultConsumer2 ，DefaultConsumer3, DefaultConsumer4  打开）
	 * 	结果： 每个消费者有可能消费一个
	 * @author lwl
	 * @create 2019年6月14日 下午1:15:48
	 */
	@Test
	public void send4() {
		int i = 0;
		String message = "我是default 发送的消息"+i;
		producer.sendDefaule(Constants.DEFALUT_QUEUE, message+i++);
		producer2.sendDefaule(Constants.DEFALUT_QUEUE, message+i++);
		producer3.sendDefaule(Constants.DEFALUT_QUEUE, message+i++);
		producer4.sendDefaule(Constants.DEFALUT_QUEUE, message+i++);
		System.out.println("--------------------------------发送完毕--------------------------------");
		System.out.println();
		System.out.println();
	}

}
