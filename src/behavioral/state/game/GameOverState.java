package behavioral.state.game;

import creational.singleton.Game;

public class GameOverState implements GameState {

    @Override
    public void execute(Game game) {
        if (game.getPlayer().isAlive()) {
            System.out.println("🎉 Congratulations! You survived the dungeon.");
        } else {
            System.out.println("💀 Game Over. You died.");
        }
        game.setRunning(false);
    }
}
