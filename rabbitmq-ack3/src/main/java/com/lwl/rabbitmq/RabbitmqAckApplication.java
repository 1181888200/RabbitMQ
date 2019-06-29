package com.lwl.rabbitmq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lwl.rabbitmq.constant.Constants;

/**
 * 
 * 设置全局确认信息
 * 
 * @author lwl
 * @create 2019年6月17日 下午1:34:45
 * @version 1.0
 */
@SpringBootApplication
public class RabbitmqAckApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqAckApplication.class, args);
	}
	

	   
    /**
     * 自动确认涉及到一个问题就是如果在处理消息的时候抛出异常，消息处理失败，但是因为自动确认而导致 Rabbit 将该消息删除了，造成消息丢失
     * @param connectionFactory
     * @return
     * @author lwl
     * @create 2019年6月17日 上午11:13:14
     */
//    @Bean
//	public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
//	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//	    container.setConnectionFactory(connectionFactory);
//	    container.setQueueNames(Constants.TOPIC_QUEUE);                 // 监听的队列
//	    container.setAcknowledgeMode(AcknowledgeMode.NONE);     // NONE 代表自动确认
//	    container.setMessageListener((MessageListener) message -> {         //消息监听处理
//	    	System.out.println();
//	    	System.out.println();
//	    	System.out.println("====接收到消息=====");
//	        System.out.println(new String(message.getBody()));
//	        System.out.println();
//	        System.out.println();
//	        //相当于自己的一些消费逻辑抛错误
//	        throw new NullPointerException("consumer fail");
//	    });
//	    return container;
//	}
    /**
     * AcknowledgeMode 除了 NONE 和 MANUAL 之外还有 AUTO ，它会根据方法的执行情况来决定是否确认还是拒绝（是否重新入queue）

		如果消息成功被消费（成功的意思是在消费的过程中没有抛出异常），则自动确认
		当抛出 AmqpRejectAndDontRequeueException 异常的时候，则消息会被拒绝，且 requeue = false（不重新入队列）
		当抛出 ImmediateAcknowledgeAmqpException 异常，则消费者会被确认
		其他的异常，则消息会被拒绝，且 requeue = true（如果此时只有一个消费者监听该队列，则有发生死循环的风险，多消费端也会造成资源的极大浪费，这个在开发过程中一定要避免的）。可以通过 setDefaultRequeueRejected（默认是true）去设置
     */
    /**
     * 手动确认消息
     * @param connectionFactory
     * @return
     * @author lwl
     * @create 2019年6月17日 下午1:01:43
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(Constants.TOPIC_QUEUE);              // 监听的队列
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);        // 手动确认
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {      //消息处理
            System.out.println("====接收到消息=====");
            System.out.println(new String(message.getBody()));
            if(message.getMessageProperties().getHeaders().get("error") == null){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                System.out.println("消息已经确认");
            }else {
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
                channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
                System.out.println("消息拒绝");
            }

        });
        return container;
    }
    
    /**
     * AUTO 根据情况确定
     * @param connectionFactory
     * @return
     * @author lwl
     * @create 2019年6月17日 下午1:14:44
     */
//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames("consumer_queue");              // 监听的队列
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO);     // 根据情况确认消息
//        container.setMessageListener((MessageListener) (message) -> {
//            System.out.println("====接收到消息=====");
//            System.out.println(new String(message.getBody()));
//            //抛出NullPointerException异常则重新入队列
//            //throw new NullPointerException("消息消费失败");
//            //当抛出的异常是AmqpRejectAndDontRequeueException异常的时候，则消息会被拒绝，且requeue=false
//            //throw new AmqpRejectAndDontRequeueException("消息消费失败");
//            //当抛出ImmediateAcknowledgeAmqpException异常，则消费者会被确认
//            throw new ImmediateAcknowledgeAmqpException("消息消费失败");
//        });
//        return container;
//    }
	
}
