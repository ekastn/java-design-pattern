package behavioral.state.game;

import behavioral.observer.EventType;
import creational.singleton.Game;

public class ExploringState implements GameState {
    @Override
    public void execute(Game game) {
        if (game.getDungeon().isComplete()) {
            Game.notify(EventType.CONGRATS, "You found the exit!");
            game.setGameState(new GameOverState());
            return;
        }

        game.getDungeon().exploreNextRoom();
    }
}