package creational.builder;

import core.Dungeon;
import creational.factorymethod.EnemyFactory;
import creational.factorymethod.PotionFactory;
import creational.factorymethod.RoomObjectFactory;
import creational.factorymethod.TrapFactory;
import structural.composite.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonBuilder {
    private int roomCount;
    private final Random random = new Random();

    public DungeonBuilder setRoomCount(int roomCount) {
        this.roomCount = roomCount;
        return this;
    }

    public Dungeon build() {
        List<Room> rooms = new ArrayList<>();
        RoomObjectFactory[] objectFactories = new RoomObjectFactory[]{
                new EnemyFactory(),
                new PotionFactory(),
                new TrapFactory()
        };

        for (int i = 0; i < roomCount; i++) {
            Room room = new Room("Room " + (i + 1));
            int objectType = random.nextInt(objectFactories.length);
            room.addObject(objectFactories[objectType].createObject());
            rooms.add(room);
        }

        rooms.getLast().setDescription("Room " + roomCount + ": you found the exit!");

        return new Dungeon(rooms);
    }
}
