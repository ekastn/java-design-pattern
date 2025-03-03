package behavioral.observer;

import utils.ConsoleUtils;

public class GameStateListener implements EventListener {
    @Override
    public void update(EventType eventType, String message) {
        if (eventType == EventType.GAME_STATE) {
            ConsoleUtils.clearConsole();
        }
    }
}
