package cn.calfgz.study.rabbitmq.api.exchange.direct;

import com.rabbitmq.client.*;

public class Consumer4DirectExchange {

	public static void main(String[] args) throws Exception {
		
		
        ConnectionFactory connectionFactory = new ConnectionFactory() ;  
        
        connectionFactory.setHost("192.168.75.57");
        connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/");
		
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);
        Connection connection = connectionFactory.newConnection();
        
        Channel channel = connection.createChannel();  
		//4 声明
		String exchangeName = "test_direct_exchange";
		String exchangeType = "direct";
		String queueName = "test_direct_queue";
		String routingKey = "test.direct";
		
		//表示声明了一个交换机
		channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
		//表示声明了一个队列
		channel.queueDeclare(queueName, false, false, false, null);
		//建立一个绑定关系:
		channel.queueBind(queueName, exchangeName, routingKey);
		
        //durable 是否持久化消息
        //参数：队列名称、是否自动ACK、Consumer
        channel.basicConsume(queueName, true, new DefaultConsumer(channel));
	}
}
