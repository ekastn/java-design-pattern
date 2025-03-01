package behavioral.strategy;

import core.Character;

public class MeleeAttack implements IAttackStrategy {
    @Override
    public void attack(Character target) {
        System.out.println("Attacking with a sword!");
        target.takeDamage(20);
    }
}
