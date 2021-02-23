package main;

import brocker.BrokerLauncher;
import consumer.JmsSyncReceiveClientExample;
import producer.JmsProducerQueueClient;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Use argument server, client, broker");
        }
        if (args[0].equals("server")) {
            JmsProducerQueueClient.createJmsProducerQueueClient().run();
        } else if (args[0].equals("client")) {
            JmsSyncReceiveClientExample.createJmsSyncReceiveClientExample().run();
        } else if (args[0].equals("broker")) {
            BrokerLauncher.createBrokerLauncher().run();
        } else {
            System.out.println("Use argument server, client, broker");
        }
    }
}
