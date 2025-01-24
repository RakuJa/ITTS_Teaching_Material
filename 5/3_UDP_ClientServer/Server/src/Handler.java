import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HandlerUDP extends Thread{


    private final DatagramSocket socket;
    private final DatagramPacket receivePacket;

    public HandlerUDP(DatagramSocket socket, DatagramPacket receivePacket) {
        this.socket = socket;
        this.receivePacket = receivePacket;
    }

    @Override
    public void run() {
        try {
            handleMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void handleMessage() throws IOException {
        String response;
        String inputMessage = new String(receivePacket.getData(), StandardCharsets.UTF_8);
        if (inputMessage.toUpperCase().startsWith("PING")) {
            response = "PONG";
        } else if (inputMessage.toUpperCase().startsWith("PONG")) {
            response = "PING";
        } else {
            response = "COMMAND NOT RECOGNIZED. TRY 'PING' OR 'PONG'";
        }
        byte[] responseData = response.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(
                responseData, responseData.length,
                receivePacket.getAddress(), receivePacket.getPort()
        );
        this.socket.send(sendPacket);

    }

}
