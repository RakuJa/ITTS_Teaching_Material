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
        // Try picking up both forks
        if (leftFork.pickUp()) {
            System.out.println("Philosopher " + name + " picked up left fork.");
            if (rightFork.pickUp()) {
                System.out.println("Philosopher " + name + " picked up right fork.");
                return true;
            } else {
                leftFork.putDown();
                System.out.println("Philosopher " + name + " put down left fork.");
            }
        }
        return false;
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + name + " is eating.");
        Thread.sleep((int) (Math.random() * 100 + 1000)); // Eating time
    }

    private void putDownForks() {
        leftFork.putDown();
        rightFork.putDown();
        System.out.println("Philosopher " + name + " put down both forks.");
    }
}
