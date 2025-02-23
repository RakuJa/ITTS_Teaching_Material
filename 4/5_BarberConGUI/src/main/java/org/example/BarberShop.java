import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

public class BarberShop extends JPanel {

    //Private Attributes
    private final String BarberShopName;
    private final HashMap<Color, Vector<Client>> chairsQueue;
    //private final Vector <Client> listOfClients;
    private final int maxSizeList;
    private final int padding = 10;
    private final int chairSize = 50;

    //Constructor
    BarberShop(String BarberShopName, int maxSizeList) {
        this.BarberShopName = BarberShopName;
        this.maxSizeList = maxSizeList;
        chairsQueue = new HashMap<>();
        chairsQueue.put(Color.RED, new Vector<>());

        // Create a button
        JButton button = new JButton("Click me!");

        // Create a label to simulate Toast message
        JLabel toastLabel = new JLabel("", SwingConstants.CENTER);
        toastLabel.setForeground(Color.WHITE);
        toastLabel.setBackground(Color.BLACK);
        toastLabel.setOpaque(true);
        toastLabel.setVisible(false);  // Initially hidden

        // Add ActionListener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the toast message text
                toastLabel.setText("There are " + getCurrentNumberOfClients() + " clients");

                // Show the toast
                toastLabel.setVisible(true);

                // Make the toast disappear after 2 seconds (2000 milliseconds)
                new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        toastLabel.setVisible(false);
                    }
                }).start();
            }
        });
        //this.add(toastLabel);
        // Add the button to the frame

        this.add(toastLabel);
        this.add(button);
        // REMEMBER: show what happens if we swap declarations
        // Add label


    }

    //Functions of Client Management
    public synchronized void addClient(Client client) {
        if (!isAcceptingNewClient()) {
            System.out.println(client.getName() + " couldn't enter - shop is closing.");
            return;
        }
        if (chairsQueue.get(Color.RED).size() < maxSizeList) {
            chairsQueue.get(Color.RED).add(client);
            System.out.println(client.getName() + " has entered the barber shop.");
            notify();
        } else {
            System.out.println("Reached max client list-");
        }
    }

    public synchronized void removeClient() {
        if (!chairsQueue.isEmpty()) {
            Client client = chairsQueue.get(Color.RED).removeFirst();
            System.out.println(client.getName() + " has left the barber shop.");
        }
    }

    public synchronized boolean isAcceptingNewClient() {
        return !Thread.currentThread().isInterrupted();
    }

    public boolean isShopFree() {
        return chairsQueue.get(Color.RED).isEmpty();
    }

    //Get Function
    public int getCurrentNumberOfClients() {
        return chairsQueue.get(Color.RED).size();
    }

    public Client getFirstClient() {
        return chairsQueue.get(Color.RED).getFirst();
    }


    @Override
    protected void paintComponent(Graphics g) {

        int numSmallRectangles = maxSizeList;

        int currNumberOfOccupiedChairs = this.getCurrentNumberOfClients();

        super.paintComponent(g);

        // Set the color for the big rectangle
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight()); // Big rectangle inside the JPanel

        // Set the color for the small rectangles
        g.setColor(Color.RED);

        // Calculate the available width for small rectangles considering the padding
        int availableWidth = this.getWidth() - (padding * (numSmallRectangles - 1));
        int smallRectWidth = availableWidth / numSmallRectangles;
        int smallRectHeight = chairSize;  // Fixed height for the small rectangles

        // Draw the small rectangles aligned in a row inside the big rectangle
        for (int i = 0; i < currNumberOfOccupiedChairs; i++) {
            int xPosition = 10 + i * (smallRectWidth + padding); // Add padding to the x-coordinate
            g.fillRect(xPosition, 10 + (this.getHeight() - smallRectHeight) / 2, smallRectWidth, smallRectHeight);
        }
        g.setColor(Color.GREEN);
        for (int i = currNumberOfOccupiedChairs; i < numSmallRectangles; ++i) {
            int xPosition = 10 + i * (smallRectWidth + padding); // Add padding to the x-coordinate
            g.fillRect(xPosition, 10 + (this.getHeight() - smallRectHeight) / 2, smallRectWidth, smallRectHeight);
        }
    }
}
