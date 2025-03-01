package creational.builder;

import behavioral.strategy.MeleeAttack;
import behavioral.strategy.MagicAttack;
import behavioral.strategy.StealthAttack;
import core.Hero;

public class HeroDirector {
    private final HeroBuilder builder;
    private final String name;

    public HeroDirector(HeroBuilder builder, String name) {
        this.builder = builder;
        this.name = name;
    }

    public Hero createWarrior() {
        return builder
                .setName(name)
                .setHealth(120)
                .setAttack(25)
                .setDefense(20)
                .setAttackStrategy(new MeleeAttack())
                .build();
    }

    public Hero createMage() {
        return builder
                .setName(name)
                .setHealth(80)
                .setAttack(30)
                .setDefense(10)
                .setAttackStrategy(new MagicAttack())
                .build();
    }

    public Hero createAssassin() {
        return builder
                .setName(name)
                .setHealth(90)
                .setAttack(35)
                .setDefense(15)
                .setAttackStrategy(new StealthAttack())
                .build();
    }

    public Hero createTank() {
        return builder
                .setName(name)
                .setHealth(150)
                .setAttack(15)
                .setDefense(30)
                .setAttackStrategy(new MeleeAttack())
                .build();
    }
}
