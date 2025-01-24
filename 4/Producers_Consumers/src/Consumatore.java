public class Consumatore extends Persona{

    private final String name;

    Consumatore(String name) {
        this.name = name;
    }

    protected synchronized void consume() {
        boolean hasConsumed = false;
        while (!hasConsumed) {
            if (canIConsume()) {
                incrementConsumers();
                hasConsumed = true;
                System.out.println(this.name + ": is consuming " + getValue());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setValue(0);
                decrementConsumers();
            }
        }

    }

    @Override
    public void run() {
        while (true) {
            consume();
        }
    }

}
