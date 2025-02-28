package character;

import dungeon.RoomObject;

public class Enemy extends Character implements RoomObject {
    public Enemy(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }

    @Override
    public void performAttack(Character target) {
        System.out.println(name + " attack " + target.getName());
        target.takeDamage(attackPower);
    }

    @Override
    public void interact(Hero hero) {}
}
