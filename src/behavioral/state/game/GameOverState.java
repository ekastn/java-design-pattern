package behavioral.state.game;

import behavioral.observer.EventType;
import creational.singleton.Game;

public class GameOverState implements GameState {

    @Override
    public void execute(Game game) {
        if (game.getPlayer().isAlive()) {
            Game.notify(EventType.CONGRATS, "Congratulations! You survived the dungeon!");
        } else {
            System.out.println("💀 Game Over. You died.");
            Game.notify(EventType.PLAYER_DEAD, "Game Over. You died.");
        }
        game.setRunning(false);
    }
}
