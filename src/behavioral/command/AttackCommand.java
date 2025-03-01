package behavioral.command;

import core.Character;

public class AttackCommand implements CombatCommand {

    @Override
    public void execute(Character attacker, Character target) {
        attacker.performAttack(target);
    }
}
