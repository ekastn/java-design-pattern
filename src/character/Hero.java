package character;

import dungeon.Potion;
import behavioral.strategy.IAttackStrategy;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Character {
    final private List<Potion> inventory;
    private IAttackStrategy attackStrategy;

    public Hero(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
        this.inventory = new ArrayList<>();
    }

    public void setAttackStrategy(IAttackStrategy attackStrategy) {
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
            System.out.println(name + " menggunakan potion. health bertambah: " + health);
        } else {
            System.out.println("Tidak ada potion di inventory.");
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
        System.out.println("Potion: " + getPotionCount());
    }

    public int getPotionCount() {
        return inventory.size();
    }
}

