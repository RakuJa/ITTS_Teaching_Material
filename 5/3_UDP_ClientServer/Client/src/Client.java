import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Arrays;

public class ClientUDP {

    private static final int serverPort = 55555;

    private final DatagramSocket socket;
    private final InetAddress serverIp;

    public ClientUDP(DatagramSocket socket) throws UnknownHostException {
        this.socket = socket;
        this.serverIp = InetAddress.getByName("localhost");
    }

    public static void main(String[] args) {
        establishConnection();
    }

    private static void establishConnection() {
        // Create a DatagramSocket (no need to bind to a specific port)

        try (DatagramSocket s = new DatagramSocket()) {
            System.out.println("Connected successfully");
            new ClientUDP(s).handleUserInput();
        } catch (IOException e) {
            System.out.println("Cannot open a socket"); // Log this
            throw new RuntimeException(e);
        }
    }

    private void handleUserInput() throws IOException {
        System.out.println("Awaiting user input...");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        do {
            userInput = br.readLine();
            byte[] sendData = userInput.getBytes();
            // Create a DatagramPacket to send the message
            DatagramPacket sendPacket = new DatagramPacket(
                    sendData, sendData.length, serverIp, serverPort);
            // Send the packet to the server
            socket.send(sendPacket);

            // Buffer to receive the server's response
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Wait for the server's response (blocking call)
            socket.receive(receivePacket);
            String serverMessage = new String(receivePacket.getData(), StandardCharsets.UTF_8);


            System.out.println(MessageFormat.format("Server response: {0}. Awaiting next input..", serverMessage));
        } while (!userInput.equalsIgnoreCase("EXIT"));
    }

}