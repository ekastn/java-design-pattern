package behavioral.state.enemy;

import core.Enemy;
import core.Hero;

public interface EnemyState {
    void execute(Enemy enemy, Hero hero);
}

