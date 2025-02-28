package dungeon;

import character.Character;
import character.Hero;

public class Trap implements RoomObject {
    final private int damage;

    public Trap(int damage) {
        this.damage = damage;
    }

    public void trigger(Character character) {
        System.out.println("⚠️ A trap has been triggered!");
        character.takeDamage(damage);
    }

    @Override
    public void interact(Hero hero) {
        trigger(hero);
    }
}
