package com.game.attack;

import java.util.Random;

public class HydroAttack implements AttackStrategy {
    private final Random random = new Random();

    @Override
    public int attack() {
        int baseDamage = 18 + random.nextInt(8);
        boolean heal = random.nextInt(100) < 20; // 20% chance to heal user
        if (heal) {
            System.out.println("💧 Healing Rain! Restores health!");
        }
        return baseDamage;
    }
}