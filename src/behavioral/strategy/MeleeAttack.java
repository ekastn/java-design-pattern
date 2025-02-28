package behavioral.strategy;

import character.Character;

public class MeleeAttack implements IAttackStrategy {
    @Override
    public void attack(Character target) {
        System.out.println("Menyerang dengan pedang!");
        target.takeDamage(20);
    }
}
