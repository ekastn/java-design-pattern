package behavioral.strategy;

import core.Character;

public class StealthAttack implements IAttackStrategy {
    @Override
    public void attack(Character target) {
        System.out.println("Attacking silently from behind!");
        target.takeDamage(25);
    }
}
