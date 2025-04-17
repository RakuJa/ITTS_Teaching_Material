package org.example.Client;

import org.example.Person;

import java.io.*;
import java.net.Socket;
import java.text.MessageFormat;

public class Client {

    private static final String serverIp = "127.0.0.1";
    private static final int serverPort = 55555;


    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public static void main(String[] args) {
        establishConnection();
    }

    private static void establishConnection() {
        try (Socket s = new Socket(serverIp, serverPort)) {
            System.out.println("Connected successfully");
            new Client(s).handleUserInput();
        } catch (IOException e) {
            System.out.println("Connection failed with given ip and port"); // Log this
            throw new RuntimeException(e);
        }
    }

    private BufferedReader initializeReader(Socket s) throws IOException {
        return new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    private PrintWriter initializeWriter(Socket s) throws IOException {
        return new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
    }

    public Client(Socket s) {
        try {
            this.reader = initializeReader(s);
            this.writer = initializeWriter(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.socket = s;
    }

    private void handleUserInput() throws IOException {
        System.out.println("Insert new Person age...");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        do {
            userInput = br.readLine();
            Person p = new Person("Name", Integer.parseInt(userInput));
            writer.println(p.JacksonSerialize());
            writer.flush();
            System.out.println(MessageFormat.format("Server response: {0}. Awaiting next input..", Person.GsonDeserialize(reader.readLine()).getAge()));
        } while (!userInput.equalsIgnoreCase("EXIT"));
    }

}