package core;

import behavioral.observer.EventType;
import behavioral.state.enemy.AggressiveState;
import behavioral.state.enemy.DefensiveState;
import behavioral.state.enemy.EnemyState;
import behavioral.state.enemy.FleeingState;
import behavioral.state.game.FightingState;
import creational.singleton.Game;
import structural.composite.RoomObject;

public class Enemy extends Character implements RoomObject {
    private EnemyState state;
    private boolean escaped = false;

    public Enemy(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
        this.state = new AggressiveState(); // Default state
    }

    public void setState(EnemyState state) {
        this.state = state;
    }

    public void setEscaped(boolean escaped) {
        this.escaped = escaped;
    }

    public boolean hasEscaped() {
        return escaped;
    }

    public void checkState() {
        if (health < 20) {
            setState(new FleeingState());
        } else if (health < 50) {
            setState(new DefensiveState());
        } else {
            setState(new AggressiveState());
        }
    }

    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (isAlive()) {
            checkState();
        }
    }

    @Override
    public void performAttack(Character target) {
        if (!escaped) {
            state.execute(this, (Hero) target);
        }
    }

    @Override
    public void interact(Hero hero) {
        Game.notify(EventType.BASIC, "You encountered an enemy: " + name + "!");
        Game.notify(EventType.BASIC, "Ready to battle! ⚔️ ");

        Game.getInstance().setGameState(new FightingState(this));
        Game.getInstance().getGameState().execute(Game.getInstance());
    }
}
