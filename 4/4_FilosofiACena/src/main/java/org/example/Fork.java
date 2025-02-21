package org.example;

public class Fork {

    public synchronized boolean pickUp() {
        // TODO: insert logic
        // It should check if the fork is already used by someone else
        // And if it's not then it should "lock it" only for that philosopher and return true
    }

    public synchronized void putDown() {
        // TODO: "Unlock" the fork, letting anyone pick it up.
    }
}
