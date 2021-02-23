package consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JmsSyncReceiveClientExample {

    private JmsSyncReceiveClientExample() {
    }

    public static JmsSyncReceiveClientExample createJmsSyncReceiveClientExample() {
        return new JmsSyncReceiveClientExample();
    }

    public void run() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://localhost:61616?wireFormat.maxInactivityDurationInitalDelay=30000");
        Connection connection = connectionFactory.createConnection();
        var session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        try {
            var queue = session.createQueue("customerQueue");

            var consumer = session.createConsumer(queue);
            connection.start();
            while (true) {
                var textMsg = (TextMessage) consumer.receive();
                System.out.println(textMsg);
                System.out.println("Received: " + textMsg.getText());
            }
        } finally {
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
