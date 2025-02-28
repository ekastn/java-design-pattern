package behavioral.state;

import character.Enemy;
import character.Hero;

public class DefensiveState implements EnemyState {
    @Override
    public void execute(Enemy enemy, Hero hero) {
        System.out.println(enemy.getName() + " is blocking and counterattacking!");
        int reducedDamage = hero.getAttackPower() / 2;
        enemy.takeDamage(reducedDamage);
        System.out.println(enemy.getName() + " counterattacks!");
        hero.takeDamage(enemy.getAttackPower());
    }
}
