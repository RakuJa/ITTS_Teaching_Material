import java.io.*;
import java.net.Socket;

public class Handler extends Thread {

    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public Handler(Socket s) {
        try {
            this.reader = initializeReader(s);
            this.writer = initializeWriter(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.socket = s;
        this.start();
    }

    private BufferedReader initializeReader(Socket s) throws IOException {
        return new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    private PrintWriter initializeWriter(Socket s) throws IOException {
        return new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
    }

    @Override
    public void run() {
        boolean keepChatting = true;
        while (keepChatting) {
            try {
                handleMessage();
            } catch (IOException e) {
                keepChatting = false;
                System.out.println(e); // Log it, not print it
            }
        }

        try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void handleMessage() throws IOException {
        String inputMessage;

        System.out.println("Awaiting client message");
        inputMessage = this.reader.readLine();
        System.out.println(inputMessage.toString());
        if (inputMessage.equalsIgnoreCase("PING")) {
            writer.println("PONG");
        } else if (inputMessage.equalsIgnoreCase("PONG")) {
            writer.println("PING");
        } else {
            writer.println("COMMAND NOT RECOGNIZED. TRY 'PING' OR 'PONG'");
        }
        writer.flush();
    }
}
