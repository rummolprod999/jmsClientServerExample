package producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.Session;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class JmsProducerQueueClient {

    private JmsProducerQueueClient() {
    }

    public static JmsProducerQueueClient createJmsProducerQueueClient() {
        return new JmsProducerQueueClient();
    }

    public void run() throws Exception {
        Connection connection = null;
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    "tcp://localhost:61616?wireFormat.maxInactivityDurationInitalDelay=30000");
            connection = connectionFactory.createConnection();
            var session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            var queue = session.createQueue("customerQueue");
            var producer = session.createProducer(queue);
            var task = "Task";
            while (true) {
                var payload = task + ThreadLocalRandom.current().nextInt();
                Message msg = session.createTextMessage(payload);
                System.out.println("Sending text '" + payload + "'");
                producer.send(msg);
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
