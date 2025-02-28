package behavioral.state;

import character.Enemy;
import character.Hero;

public class FleeingState implements EnemyState {
    @Override
    public void execute(Enemy enemy, Hero hero) {
        System.out.println(enemy.getName() + " is trying to escape!");
        if (Math.random() > 0.5) {
            System.out.println(enemy.getName() + " successfully escaped!");
            enemy.setEscaped(true);
        } else {
            System.out.println(enemy.getName() + " failed to escape and must fight!");
            hero.takeDamage(enemy.getAttackPower());
        }
    }
}
