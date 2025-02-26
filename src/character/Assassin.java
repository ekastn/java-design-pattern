package character;

import strategy.StealthAttack;

public class Assassin extends Hero {
    public Assassin(String name) {
        super(name, 90, 35, 15);
        setAttackStrategy(new StealthAttack());
    }
}
