package behavioral.strategy;

import behavioral.observer.EventType;
import core.Character;
import creational.singleton.Game;

public class StealthAttack implements AttackStrategy {
    @Override
    public void attack(Character target) {
        Game.notify(EventType.ATTACK_STEALTH, "Attacking " + target.getName() + " from behind!");
        target.takeDamage(25);
    }
}
