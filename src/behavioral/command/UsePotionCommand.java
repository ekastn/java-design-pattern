package behavioral.command;

import core.Hero;

public class UsePotionCommand implements Command {
    private Hero hero;

    public UsePotionCommand(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void execute() {
        hero.usePotion();
    }
}
