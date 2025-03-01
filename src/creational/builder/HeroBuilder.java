package creational.builder;

import behavioral.strategy.IAttackStrategy;
import core.Hero;

public class HeroBuilder {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private IAttackStrategy attackStrategy;

    public HeroBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HeroBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    public HeroBuilder setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public HeroBuilder setDefense(int defense) {
        this.defense = defense;
        return this;
    }

    public HeroBuilder setAttackStrategy(IAttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
        return this;
    }

    public Hero build() {
        Hero hero = new Hero(name, health, attack, defense);
        hero.setAttackStrategy(attackStrategy);
        return hero;
    }
}
