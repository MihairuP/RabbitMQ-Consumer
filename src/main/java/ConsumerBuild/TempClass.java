package ConsumerBuild;

public class TempClass {
    public static void main(String[] args) {
        String hostIP = "localhost";
        String exchange = "NamedExchange";
        Consumer consumer = new Consumer();
        try {
            consumer.receiveMessages(hostIP, exchange);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//sudo rabbitmqctl list_bindings
//sudo rabbitmqctl list_exchanges