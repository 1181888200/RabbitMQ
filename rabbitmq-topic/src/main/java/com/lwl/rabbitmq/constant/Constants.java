package com.lwl.rabbitmq.constant;

/**
 * 常量
 * @author lwl
 * @create 2019年6月14日 下午12:28:56
 * @version 1.0
 */
public class Constants {

	//使用TOPIC交换机 队列
	public static final String TOPIC_QUEUE = "topic_queue";
	public static final String TOPIC_QUEUE_TWO = "topic_queue_2";
	
	//TOPIC 名称
	public static final String TOPIC_NAME = "topic.queue.exchange";
	
	public static final String ROUTING_KEY = "topic.queue.key";
	
	public static final String ROUTING_KEY_TWO = "topic.*.#";
}
