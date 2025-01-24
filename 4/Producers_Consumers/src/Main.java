public class Main {
    public static void main(String[] args) {

        System.out.println("Hello, World!");

        Produttore p0 = new Produttore("p0");
        Produttore p1 = new Produttore("p1");
        Produttore p2 = new Produttore("p2");

        Consumatore c0 = new Consumatore("c0");
        Consumatore c1 = new Consumatore("c1");
        Consumatore c2 = new Consumatore("c2");

        p0.start();
        p1.start();
        p2.start();

        c0.start();
        c1.start();
        c2.start();
    }
}