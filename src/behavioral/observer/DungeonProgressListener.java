package behavioral.observer;

import utils.ConsoleUtils;

public class DungeonProgressListener implements EventListener {
    @Override
    public void update(EventType eventType, String message) {
        if (eventType == EventType.DUNGEON_PROGRESS || eventType == EventType.ROOM_EXPLORED) {
            ConsoleUtils.clearConsole();
            System.out.println(eventType.icon + " " + message);
        }
    }
}
