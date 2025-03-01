package behavioral.strategy;

import core.Character;

public class MagicAttack implements IAttackStrategy {
    @Override
    public void attack(Character target) {
        System.out.println("Attacking with magic!");
        target.takeDamage(30);
    }
}
