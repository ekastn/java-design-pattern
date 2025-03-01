package structural.composite;

import behavioral.observer.EventType;
import core.Character;
import core.Hero;
import creational.singleton.Game;

public class Trap implements RoomObject {
    final private int damage;

    public Trap(int damage) {
        this.damage = damage;
    }

    public void trigger(Character character) {
        Game.notify(EventType.WARNING, "A trap has been triggered!");
        character.takeDamage(damage);
    }

    @Override
    public void interact(Hero hero) {
        trigger(hero);
    }
}
