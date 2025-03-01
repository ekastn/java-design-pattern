package behavioral.state.enemy;

import behavioral.observer.EventType;
import core.Enemy;
import core.Hero;
import creational.singleton.Game;

public class FleeingState implements EnemyState {
    @Override
    public void execute(Enemy enemy, Hero hero) {
        Game.notify(EventType.WARNING, enemy.getName() + " is trying to escape!");
        if (Math.random() > 0.5) {
            Game.notify(EventType.CHARACTER_FLEEING, enemy.getName() + " has escaped!");
            enemy.setEscaped(true);
        } else {
            Game.notify(EventType.BASIC, enemy.getName() + " failed to escape and must fight!");
            hero.takeDamage(enemy.getAttackPower());
        }
    }
}
