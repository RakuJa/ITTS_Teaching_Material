
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int DEFAULT_SERVICE_PORT = 55555;


    public static void main(String[] argv) {
        try {
            startServer();
            System.out.println("ciao");
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }

    public static void startServer() throws IOException {
        try (ServerSocket server = new ServerSocket(DEFAULT_SERVICE_PORT)) {
            System.out.println("Opening port...");
            while (true) {
                Socket s = server.accept();
                System.out.println("A client connected...");
                new Handler(s);
            }
        } catch (IOException e) {
            System.out.println("Problems encountered opening port,Server closing..");
        }
    }

}
