import java.util.Random;

public class Persona extends Thread{

    private static int consumerQueue = 0;
    private static int producerQueue = 0;

    static Random rand = new Random();

    private static int value = 0;

    protected static synchronized boolean canIConsume() {
        return producerQueue == 0 && value != 0;
    }

    protected static synchronized boolean canIProduce() {
        // No one is already producing && no one is consuming
        return consumerQueue == 0 && producerQueue == 0 && value == 0;
    }

    protected static synchronized void incrementConsumers() {
        ++consumerQueue;
    }

    protected static synchronized void decrementConsumers() {
        --consumerQueue;
    }

    protected static synchronized void incrementProducers() {
        ++producerQueue;
    }

    protected static synchronized void decrementProducers() {
        --producerQueue;
    }

    protected static synchronized void setValue(int val) {
        value = val;
        System.out.println("Value changed to " + value);
    }

    protected static synchronized int getValue() {
        return value;
    }

}
