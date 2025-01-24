
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerUDP {

    private static final int DEFAULT_SERVICE_PORT = 55555;

    private static final int MAX_THREADS = 10;  // Maximum number of concurrent threads
    private static ExecutorService threadPool;

    public static void main(String[] argv) {
        try {
            // Create a thread pool to handle multiple clients
            threadPool = Executors.newFixedThreadPool(MAX_THREADS);
            startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void startServer() throws IOException {
        try (DatagramSocket server = new DatagramSocket(DEFAULT_SERVICE_PORT)) {
            System.out.println("Opening port...");
            byte[] receiveData = new byte[1024];
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                server.receive(receivePacket);
                threadPool.submit(new HandlerUDP(server, receivePacket));
            }
        } catch (IOException e) {
            System.out.println("Problems encountered opening port,Server closing..");
        }
    }

}
