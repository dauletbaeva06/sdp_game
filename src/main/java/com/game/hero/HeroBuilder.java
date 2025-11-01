package com.game.hero;

public class HeroBuilder {
    private String name;
    private String element;
    private String weapon;
    private int health = 100;

    public HeroBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HeroBuilder setElement(String element) {
        this.element = element;
        return this;
    }

    public HeroBuilder setWeapon(String weapon) {
        this.weapon = weapon;
        return this;
    }

    public HeroBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    public Hero build() {
        if (name == null || element == null || weapon == null) {
            throw new IllegalStateException("Hero must have name, element, and weapon!");
        }
        return new Hero(name, element, weapon, health);
    }
}
