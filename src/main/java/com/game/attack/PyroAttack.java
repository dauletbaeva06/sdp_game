package com.game.attack;

import java.util.Random;

public class PyroAttack implements AttackStrategy {
    private final Random random = new Random();

    @Override
    public int attack() {
        int baseDamage = 25 + random.nextInt(10);
        boolean burn = random.nextInt(100) < 25; // 25% chance
        if (burn) {
            System.out.println("🔥 Pyro Burst! The target is burning!");
            baseDamage += 10;
        }
        return baseDamage;
    }
}