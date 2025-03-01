package behavioral.command;

import core.Character;

public class DefendCommand implements CombatCommand {

    @Override
    public void execute(Character attacker, Character target) {
        System.out.println(target.getName() + " defends, reducing damage!");
        int reducedDamage = attacker.getAttackPower() / 2;
        target.takeDamage(reducedDamage);
    }
}
