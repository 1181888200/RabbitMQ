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
	
	@Test
	public void send() {
		String message = " message with ack C4 ";
		ackProducer.send(message );
		
	}
	@Test
	public void send2() {
	}

}
