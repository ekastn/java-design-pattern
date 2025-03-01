package behavioral.command;

import core.Character;
import core.Hero;

public class UsePotionCommand implements CombatCommand {
    private final Hero hero;

    public UsePotionCommand(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void execute(Character attacker, Character target) {
        hero.usePotion();
    }
}
