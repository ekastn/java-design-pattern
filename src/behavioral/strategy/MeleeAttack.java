package behavioral.strategy;

import behavioral.observer.EventType;
import core.Character;
import creational.singleton.Game;

public class MeleeAttack implements AttackStrategy {
    @Override
    public void attack(Character target) {
        Game.notify(EventType.ATTACK_MELEE, "Attacking " + target.getName() + " with a sword!");
        target.takeDamage(20);
    }
}
