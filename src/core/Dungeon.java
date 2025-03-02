package core;

import behavioral.iterator.RoomIterator;
import behavioral.observer.EventType;
import creational.singleton.Game;
import structural.composite.Room;
import utils.ConsoleUtils;

import java.util.List;

public class Dungeon {
    private final RoomIterator iterator;

    public Dungeon(List<Room> rooms) {
        this.iterator = new RoomIterator(rooms);
    }

    public void startExploration() {
        if (iterator.hasNext()) {
            exploreNextRoom();
        } else {
            Game.notify(EventType.WARNING, "The dungeon is empty!");
        }
    }

    public void exploreNextRoom() {
        ConsoleUtils.clearConsole();
        if (iterator.hasNext()) {
            Room nextRoom = iterator.next();
            nextRoom.explore(Game.getInstance().getPlayer());
        } else {
            Game.notify(EventType.WARNING, "No more rooms left in the dungeon.");
        }
    }

    public boolean isComplete() {
        return !iterator.hasNext();
    }
}
