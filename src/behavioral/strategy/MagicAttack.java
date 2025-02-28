package behavioral.strategy;

import character.Character;

public class MagicAttack implements IAttackStrategy {
    @Override
    public void attack(Character target) {
        System.out.println("Menyerang dengan sihir!");
        target.takeDamage(30);
    }
}
