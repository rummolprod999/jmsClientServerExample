package brocker;

import org.apache.activemq.broker.BrokerFactory;

import java.net.URI;

public class BrokerLauncher {
    private BrokerLauncher() {
    }

    public static BrokerLauncher createBrokerLauncher() {
        return new BrokerLauncher();
    }

    public void run() throws Exception {
        var broker = BrokerFactory.createBroker(new URI(
                "broker:(tcp://localhost:61616)"));
        broker.start();
    }
}
