package com.game.attack;

import java.util.Random;

public class CryoAttack implements AttackStrategy {
    private final Random random = new Random();

    @Override
    public int attack() {
        int damage = 17 + random.nextInt(7);
        boolean freeze = random.nextInt(100) < 15;
        if (freeze) {
            System.out.println("❄️ Frozen Strike! The enemy is slowed!");
            damage += 5;
        }
        return damage;
    }
}
