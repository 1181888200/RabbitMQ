package com.lwl.rabbitmq;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lwl.rabbitmq.producer.AckProducer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={RabbitmqAckApplication.class})
@WebAppConfiguration
public class RabbitmqAckApplicationTests {

	@Resource
	private AckProducer ackProducer;
	
	/**
	 * 把（NackTopicAckConsumer 和 TopicAckConsumer ） 2个消费者注释掉，保证数据发送到队列中
	 * @author lwl
	 * @create 2019年6月29日 上午9:47:39
	 */
	@Test
	public void send() {
		String message = " message with ack C3 ";
		ackProducer.send(message );
	}
	
	
	/**
	 * 开启消费者 NackTopicAckConsumer , 但是我们将确认回复注释掉，看看运行结果
	 * @author lwl
	 * @create 2019年6月29日 上午9:50:26
	 */
	@Test
	public void send2() {
	}

	/**
	 * 开启消费者 NackTopicAckConsumer , 我们将确认回复注释掉去掉，看看运行结果
	 * @author lwl
	 * @create 2019年6月29日 上午9:50:26
	 */
	@Test
	public void send3() {
	}

}
