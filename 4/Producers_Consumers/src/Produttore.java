public class Produttore extends Persona{

    private final String name;

    Produttore(String name) {
        this.name = name;
        //this.name = ""+this.threadId();
    }

    protected synchronized void produce() {
        boolean hasWritten = false;
        while (!hasWritten) {
            if (canIProduce()) {
                incrementProducers();
                hasWritten = true;
                int nextProd = rand.nextInt(50) + 1;
                System.out.println(this.name + ": is producing " + nextProd);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setValue(nextProd);
                decrementProducers();
            }
        }

    }

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }

}
