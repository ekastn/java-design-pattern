package dungeon;

import character.Character;

public class Trap {
    final private int damage;

    public Trap(int damage) {
        this.damage = damage;
    }

    public void trigger(Character character) {
        System.out.println("Perangkap terpicu!");
        character.takeDamage(damage);
    }
}
