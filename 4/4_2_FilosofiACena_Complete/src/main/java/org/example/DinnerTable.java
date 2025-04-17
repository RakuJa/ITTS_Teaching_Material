package org.example;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class DinnerTable {

    final static Map<String, List<String>> philosophers = Map.of(
            "4C", List.of(
                    "Caburlotto", "Chegri", "Cimpoies", "Dei Rossi",
                    "Er Rmili", "Finotto", "Gardin", "Guadagnin", "Moraru",
                    "Napastyuk", "Parpinel", "Pavan", "Perissinotto", "Riolfo",
                    "Sadiku", "Santin", "Singh", "Stanciu", "Zaninello"
            ),
            "4B", List.of(
                    "Aneka", "Barbieri", "Bincoletto", "Boso", "Burato", "Ceaglei",
                    "Gabbana", "Katia", "Masenello", "Meneghetti", "Modolo", "Morsli",
                    "Murador", "Nardin", "Pop", "Sacconato", "Secco", "Sologub", "Tonetto",
                    "Tonolo", "Vallese", "ZagoA", "ZagoG"
            )
    );

    public static void main(String[] args) {
        System.out.println("What classes are your philosophers in? (4B or 4C)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        Vector<String> philosophersNames = new Vector<>(philosophers.get(input.toUpperCase()));
        int numberOfPhilosophers = 8;
        // Creates Vectors to store N forks and N philosophers
        Fork[] forks = new Fork[numberOfPhilosophers];
        Philosopher[] philosophers = new Philosopher[numberOfPhilosophers];

        // Initialize forks
        for (int i = 0; i < numberOfPhilosophers; i++) {
            forks[i] = new Fork();
        }

        // Initialize philosophers
        for (int i = 0; i < numberOfPhilosophers; i++) {
            int rando = (int) ((Math.random() * philosophersNames.size()));
            String name = philosophersNames.remove(rando);
            // Each philosopher has two adjacent forks: left and right
            philosophers[i] = new Philosopher(
                    name, forks[i], forks[(i + 1) % numberOfPhilosophers]
            );
            philosophers[i].start();
        }
    }
}

