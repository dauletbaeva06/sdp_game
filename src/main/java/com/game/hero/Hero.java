package com.game.hero;

import com.game.attack.*;
import com.game.observer.*;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    private final String name;
    private final String element;
    private final String weapon;
    private int health;
    private AttackStrategy attackStrategy;
    private final List<Observer> observers = new ArrayList<>();

    public Hero(String name, String element, String weapon, int health) {
        this.name = name;
        this.element = element;
        this.weapon = weapon;
        this.health = health;
    }

    public String getName() { return name; }
    public boolean isAlive() { return health > 0; }

    public String getElement() { return element;}
    public String getWeapon() { return weapon; }
    public int getHealth() { return health; }

    public void registerObserver(Observer o) { observers.add(o); }

    private void notifyObservers(String event) {
        for (Observer o : observers) o.update(event);
    }

    public void setAttackStrategy(AttackStrategy strategy) {
        this.attackStrategy = strategy;
        notifyObservers(name + " switched to " + strategy.getClass().getSimpleName());
    }

    public void attack(Hero target) {
        if (attackStrategy == null) {
            notifyObservers(name + " has no attack strategy!");
            return;
        }
        int damage = attackStrategy.attack();
        target.takeDamage(damage);
        notifyObservers("✨ " + name + " used " + element + " attack on " + target.name + " for " + damage + " damage!");
    }

    private void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            notifyObservers("💀 " + name + " has been defeated!");
        } else {
            notifyObservers(name + " has " + health + " HP left.");
        }
    }
}
