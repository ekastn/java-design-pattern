package character;

import strategy.MeleeAttack;

public class Warrior extends Hero {
    public Warrior(String name) {
        super(name, 120, 25, 20);
        setAttackStrategy(new MeleeAttack());
    }
}
