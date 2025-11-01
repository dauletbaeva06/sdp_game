package com.game;

import com.game.attack.*;
import com.game.hero.*;
import com.game.observer.*;

import java.util.Scanner;

public class Runner {

    public static void runGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("🌍 Welcome to Elemental Battle Game (Genshin Style)!");
        System.out.println("=============================================");

        System.out.print("Enter your hero name: ");
        String name = sc.nextLine();

        // --- Choose player element ---
        System.out.println("\nChoose your element: ");
        System.out.println("1. Pyro 🔥");
        System.out.println("2. Hydro 💧");
        System.out.println("3. Electro ⚡");
        System.out.println("4. Cryo ❄️");
        System.out.print("Enter choice: ");
        int elementChoice = sc.nextInt();
        sc.nextLine(); // consume newline

        String element;
        AttackStrategy strategy;

        switch (elementChoice) {
            case 1 -> { element = "Pyro"; strategy = new PyroAttack(); }
            case 2 -> { element = "Hydro"; strategy = new HydroAttack(); }
            case 3 -> { element = "Electro"; strategy = new ElectroAttack(); }
            case 4 -> { element = "Cryo"; strategy = new CryoAttack(); }
            default -> {
                element = "Pyro";
                strategy = new PyroAttack();
            }
        }

        System.out.print("\nChoose your weapon: ");
        String weapon = sc.nextLine();

        // --- Build player hero ---
        Hero player = new HeroBuilder()
                .setName(name)
                .setElement(element)
                .setWeapon(weapon)
                .setHealth(120)
                .build();

        // --- Multiple enemy options ---
        System.out.println("\nChoose your enemy:");
        System.out.println("1. Abyss Mage (Hydro) 💧");
        System.out.println("2. Fatui Agent (Electro) ⚡");
        System.out.println("3. Ruin Guard (Cryo) ❄️");
        System.out.print("Enter choice: ");
        int enemyChoice = sc.nextInt();

        Hero enemy;
        switch (enemyChoice) {
            case 1 -> enemy = new HeroBuilder()
                    .setName("Abyss Mage")
                    .setElement("Hydro")
                    .setWeapon("Staff")
                    .setHealth(100)
                    .build();
            case 2 -> enemy = new HeroBuilder()
                    .setName("Fatui Agent")
                    .setElement("Electro")
                    .setWeapon("Dagger")
                    .setHealth(110)
                    .build();
            case 3 -> enemy = new HeroBuilder()
                    .setName("Ruin Guard")
                    .setElement("Cryo")
                    .setWeapon("Claws")
                    .setHealth(130)
                    .build();
            default -> enemy = new HeroBuilder()
                    .setName("Hilichurl")
                    .setElement("Pyro")
                    .setWeapon("Club")
                    .setHealth(90)
                    .build();
        }

        // --- Add Observer for battle events ---
        BattleObserver observer = new BattleObserver();
        player.registerObserver(observer);
        enemy.registerObserver(observer);

        // --- Set strategies ---
        player.setAttackStrategy(strategy);
        switch (enemy.getElement()) {
            case "Hydro" -> enemy.setAttackStrategy(new HydroAttack());
            case "Electro" -> enemy.setAttackStrategy(new ElectroAttack());
            case "Cryo" -> enemy.setAttackStrategy(new CryoAttack());
            default -> enemy.setAttackStrategy(new PyroAttack());
        }

        System.out.println("\n⚔️ Battle begins! " + player.getName() + " vs " + enemy.getName());
        System.out.println("=============================================");

        // --- Battle loop ---
        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\nChoose your action:");
            System.out.println("1. Attack");
            System.out.println("2. Change Element");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> player.attack(enemy);
                case 2 -> {
                    System.out.println("Select new element:");
                    System.out.println("1. Pyro 🔥  2. Hydro 💧  3. Electro ⚡  4. Cryo ❄️");
                    int newChoice = sc.nextInt();
                    player.setAttackStrategy(switch (newChoice) {
                        case 1 -> new PyroAttack();
                        case 2 -> new HydroAttack();
                        case 3 -> new ElectroAttack();
                        case 4 -> new CryoAttack();
                        default -> new PyroAttack();
                    });
                }
                default -> System.out.println("Invalid choice.");
            }

            if (enemy.isAlive()) {
                System.out.println("💥 " + enemy.getName() + " attacks!");
                enemy.attack(player);
            }
        }

        System.out.println("=============================================");
        if (player.isAlive()) {
            System.out.println("🏆 Victory! You defeated " + enemy.getName() + "!");
        } else {
            System.out.println("💀 Defeat! You were defeated by " + enemy.getName() + ".");
        }

        System.out.println("=============================================");
        sc.close();
    }
}