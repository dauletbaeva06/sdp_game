package com.game.attack;

import java.util.Random;

public class ElectroAttack implements AttackStrategy {
    private final Random random = new Random();

    @Override
    public int attack() {
        int damage = 20 + random.nextInt(12);
        boolean chain = random.nextInt(100) < 30;
        if (chain) {
            System.out.println("⚡ Chain Lightning! Hits multiple targets!");
            damage += 10;
        }
        return damage;
    }
}