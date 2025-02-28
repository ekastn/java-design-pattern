package behavioral.strategy;

import character.Character;

public class MagicAttack implements IAttackStrategy {
    @Override
    public void attack(Character target) {
        System.out.println("Attacking with magic!");
        target.takeDamage(30);
    }
}
