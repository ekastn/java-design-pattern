package behavioral.command;


import core.Character;

public class AttackCommand implements Command{
    private Character attacker;
    private Character target;

    public AttackCommand(Character attacker, Character target) {
        this.attacker = attacker;
        this.target = target;
    }

    public void setAttacker(Character attacker) {
        this.attacker = attacker;
    }

    public void setTarget(Character target) {
        this.target = target;
    }

    @Override
    public void execute() {
        attacker.performAttack(target);
    }
}
