package behavioral.state;

import core.Enemy;
import core.Hero;

public class AggressiveState implements EnemyState {
    @Override
    public void execute(Enemy enemy, Hero hero) {
        System.out.println(enemy.getName() + " is attacking aggressively!");
        hero.takeDamage(enemy.getAttackPower());
    }
}
