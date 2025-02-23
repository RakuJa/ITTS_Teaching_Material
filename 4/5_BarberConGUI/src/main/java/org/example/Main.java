import javax.swing.*;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner my_scan = new Scanner(System.in);

        //Management of the incoming data
        System.out.println("Insert the name of the barberShop: ");
        String name = my_scan.nextLine();

        System.out.println("Max size of the barberShop: ");
        int maxSize = my_scan.nextInt();
        my_scan.nextLine();

        System.out.println("Name of the barber: ");
        String barberName = my_scan.nextLine();

        Barber barber1 = new Barber(barberName);
        BarberShop barberShop = new BarberShop(name, maxSize);
        barber1.setBarberShop(barberShop);

        // Added by Prof.
        // Set up the frame
        JFrame frame = new JFrame("Barber's shop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(barberShop);
        frame.setVisible(true);

        // Added by Prof.
        Inspector inspector = new Inspector();
        inspector.inspect(barberShop);

        Thread barberThread = new Thread(barber1);
        barberThread.start();

        //Barber Shop Management
        try{
            int i = 1;
            while (i <= 100) {
                Client new_client = new Client("T" + i);
                barberShop.addClient(new_client);
                i++;
                Thread.sleep(1500);
            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }finally{
            my_scan.close();
            inspector.goHome();
            barber1.stopBarber();
        }
    }
}