package behavioral.state.enemy;

import behavioral.observer.EventType;
import core.Enemy;
import core.Hero;
import creational.singleton.Game;

public class AggressiveState implements EnemyState {
    @Override
    public void execute(Enemy enemy, Hero hero) {
        Game.notify(EventType.ATTACK_MELEE, enemy.getName() + " is attacking aggressively!");
        hero.takeDamage(enemy.getAttackPower());
    }
}
