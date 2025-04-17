package org.example;

public class Fork {
    private boolean isAvailable = true;

    public synchronized boolean pickUp() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    public synchronized void putDown() {
        isAvailable = true;
    }
}
