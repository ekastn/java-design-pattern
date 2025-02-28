package behavioral.state;

import character.Enemy;
import character.Hero;

public interface EnemyState {
    void execute(Enemy enemy, Hero hero);
}

