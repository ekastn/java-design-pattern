package behavioral.state.game;

import creational.singleton.Game;

public class ExploringState implements GameState {
    @Override
    public void execute(Game game) {
        if (game.getDungeon().isComplete()) {
            System.out.println("🎉 You found the exit!");
            game.setGameState(new GameOverState());
            return;
        }

        game.getDungeon().exploreNextRoom();
    }
}