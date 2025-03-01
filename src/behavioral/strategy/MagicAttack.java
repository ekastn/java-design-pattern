package behavioral.strategy;

import behavioral.observer.EventType;
import core.Character;
import creational.singleton.Game;

public class MagicAttack implements AttackStrategy {
    @Override
    public void attack(Character target) {
        Game.notify(EventType.ATTACK_MAGIC ,"attacking " + target.getName() + " with magic!");
        target.takeDamage(30);
    }
}
