package behavioral.command;

import core.Character;

public interface CombatCommand {
    void execute(Character attacker, Character target);
}
