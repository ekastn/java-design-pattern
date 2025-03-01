package structural.composite;

import behavioral.observer.EventType;
import core.Hero;
import creational.singleton.Game;

public class Potion implements RoomObject {
    private int healingPower;

    public Potion(int healingPower) {
        this.healingPower = healingPower;
    }

    public int getHealingPower() {
        return healingPower;
    }

    @Override
    public void interact(Hero hero) {
        Game.notify(EventType.POTION_ADDED, "You found a potion!");
        hero.addPotion(this);
    }
}
