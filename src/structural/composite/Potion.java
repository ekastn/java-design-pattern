package structural.composite;

import character.Hero;

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
        System.out.println("🧪 You found a potion!");
        hero.addPotion(this);
    }
}
