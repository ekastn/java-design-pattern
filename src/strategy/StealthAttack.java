package strategy;

import character.Character;

public class StealthAttack implements IAttackStrategy {
    @Override
    public void attack(Character target) {
        System.out.println("Menyerang diam-diam dari belakang!");
        target.takeDamage(25);
    }
}
