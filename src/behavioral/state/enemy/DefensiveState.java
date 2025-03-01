package behavioral.state.enemy;

import behavioral.observer.EventType;
import core.Enemy;
import core.Hero;
import creational.singleton.Game;

public class DefensiveState implements EnemyState {
    @Override
    public void execute(Enemy enemy, Hero hero) {
        Game.notify(EventType.CHARACTER_DEFENDED, enemy.getName() + " is blocking!");
        int reducedDamage = hero.getAttackPower() / 2;
        enemy.takeDamage(reducedDamage);
        Game.notify(EventType.ATTACK_MELEE, enemy.getName() + " counterattacks!");
        hero.takeDamage(enemy.getAttackPower());
    }
}
