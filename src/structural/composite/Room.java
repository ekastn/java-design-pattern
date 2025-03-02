package structural.composite;

import behavioral.observer.EventType;
import behavioral.state.game.FightingState;
import core.Enemy;
import core.Hero;
import creational.singleton.Game;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String description;
    private final List<RoomObject> objects;

    public Room(String description) {
        this.description = description;
        this.objects = new ArrayList<>();
    }

    public void explore(Hero hero) {
        Game.notify(EventType.ROOM_EXPLORED, "Exploring " + description + "...");

        for (RoomObject obj : objects) {
            obj.interact(hero);
        }
    }

    public void addObject(RoomObject object) {
        objects.add(object);
    }

    public List<RoomObject> getObjects() {
        return objects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
