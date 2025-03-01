package behavioral.state.game;

import creational.singleton.Game;

public interface GameState {
    void execute(Game game);
}
