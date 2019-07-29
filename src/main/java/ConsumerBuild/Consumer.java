package ConsumerBuild;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumer {
    public void receiveMessages(String hostIP, String exchange) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(hostIP);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchange, "fanout");
        //Fanout is a type of exchange that sends messages to all queues that binded to it
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchange, "");

        System.out.println(" Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String published = new String(delivery.getBody(), "UTF-8");
            String whoSent = published.substring(0, published.indexOf(':'));
            String message = published.substring(whoSent.length());
            System.out.println(" Received '" + message + "'\t\tSent by: " + whoSent);
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }

}
