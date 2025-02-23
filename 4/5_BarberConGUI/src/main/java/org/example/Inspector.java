import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Inspector extends Person {

    private PrintWriter clipboard;
    private BarberShop shop;

    private boolean inspecting = false;

    public Inspector() {
        try {
            FileWriter fw = new FileWriter("inspector_notes.csv", false);
            BufferedWriter bw = new BufferedWriter(fw);
            clipboard = new PrintWriter(bw);
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    public void inspect(BarberShop shop) {
        this.shop = shop;
        inspecting = true;
        this.start();
    }

    private String checkWatch() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time

        return now.format(formatter);
    }

    private void writeToClipboard(int queueSize, String time) {
        clipboard.println(time + ";" + queueSize);
    }

    public void goHome() {
        this.inspecting = false;
    }

    @Override
    public void run() {
        while (inspecting) {
            String time = checkWatch();
            int queueSize = shop.getCurrentNumberOfClients();

            writeToClipboard(queueSize, time);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            shop.repaint();

        }
        clipboard.close();
    }


}
