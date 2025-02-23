import java.util.Random;
import java.util.Vector;

public class Barber extends Person {

    //Private Attributes
    private final String name;
    private BarberShop barberShop;
    private boolean running;

    //Constructor and Set Methods
    Barber(String name){
        this.name = name;
        this.running = true;
    }

    public void setBarberShop(BarberShop barberShop){
        this.barberShop = barberShop;
    }

    //Action of the Barber
    public synchronized  void stopBarber(){
        running = false;
        synchronized(barberShop){
            barberShop.notify();
        }
    }

    private synchronized boolean isRunning(){
        return running;
    }

    public void entrance(){
        System.out.println(this.name + " the barber is ready to begin");
    }

    public void isWorking(){
        System.out.println(this.name + " the barber is working");
    }

    public void isNotWorking(){
        System.out.println(this.name + " the barber is resting");
    }

    public void isCutting(Client thisClient){
        System.out.println(this.name + " the barber is cutting...");
        try{
            Random random = new Random();
            Thread.sleep(random.nextInt(2000)+1000);
        }catch(InterruptedException excp){
            Thread.currentThread().interrupt();
        }
        System.out.println(this.name + " finished cutting " + thisClient.getName() + "'s hair.");
    }

    //Run Function
    @Override
    public void run() {
        while(isRunning()||!barberShop.isShopFree()){
            Client thisClient = null;

            synchronized(barberShop){
                while (barberShop.isShopFree() && isRunning()) {
                    isNotWorking();
                    try {
                        barberShop.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if(!barberShop.isShopFree()){
                    entrance();
                    thisClient = barberShop.getFirstClient();
                }
            }
            if(thisClient != null){
                isWorking();
                isCutting(thisClient);
                synchronized(barberShop){
                    barberShop.removeClient();
                }
            }
        }
        System.out.println(this.name + " the barber is closing the barber shop.");
    }
}
