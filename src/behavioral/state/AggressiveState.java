package behavioral.state;

import character.Enemy;
import character.Hero;

public class AggressiveState implements EnemyState {
    @Override
    public void execute(Enemy enemy, Hero hero) {
        System.out.println(enemy.getName() + " is attacking aggressively!");
        hero.takeDamage(enemy.getAttackPower());
    }
}
