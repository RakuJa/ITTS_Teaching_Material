package org.example;

public class Philosopher extends Thread {
    private final String name;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(String name, Fork leftFork, Fork rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                if (pickUpBothForks()) {
                    eat();
                    putDownForks();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + name + " is thinking.");
        Thread.sleep((int) (Math.random() * 100 + 3000)); // Thinking time
    }

    private boolean pickUpBothForks() throws InterruptedException {
        // TODO: Try picking up both forks
        // If it manages to do so it returns true, otherwise false
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + name + " is eating.");
        Thread.sleep((int) (Math.random() * 100 + 1000)); // Eating time
    }

    private void putDownForks() {
        // TODO: implement logic to put down both forks
        System.out.println("Philosopher " + name + " put down both forks.");
    }
}
