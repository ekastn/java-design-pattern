package core;

import behavioral.observer.EventType;
import creational.singleton.Game;
import structural.composite.Potion;
import behavioral.strategy.AttackStrategy;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Character {
    final private List<Potion> inventory;
    private AttackStrategy attackStrategy;

    public Hero(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
        this.inventory = new ArrayList<>();
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    @Override
    public void performAttack(Character target) {
        if (attackStrategy != null) {
            attackStrategy.attack(target);
        }
    }

    public void usePotion() {
        if (!inventory.isEmpty()) {
            Potion potion = inventory.removeFirst();
            health += potion.getHealingPower();
            Game.notify(EventType.POTION_USED, "You used a potion. Health increased: " + health);
        } else {
            Game.notify(EventType.WARNING, "You don't have any potion.");
        }
    }

    public void addPotion(Potion potion) {
        inventory.add(potion);
    }

    public void info() {
        System.out.println("Hero: " + name);
        System.out.println("Health: " + health);
        System.out.println("Attack: " + attackPower);
        System.out.println("Defense: " + defense);
        System.out.println("Potions: " + getPotionCount());
    }

    public int getPotionCount() {
        return inventory.size();
    }
}

