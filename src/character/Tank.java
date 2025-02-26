package character;

import strategy.MeleeAttack;

public class Tank extends Hero {
    public Tank(String name) {
        super(name, 150, 15, 30);
        setAttackStrategy(new MeleeAttack());
    }
}
