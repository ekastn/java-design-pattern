package core;

import behavioral.iterator.RoomIterator;
import creational.factorymethod.*;
import creational.singleton.Game;
import structural.composite.Room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Dungeon {
    private final List<Room> rooms;
    private final Random random;
    private final RoomObjectFactory[] objectFactories;
    private RoomIterator iterator;

    public Dungeon(int roomCount) {
        this.rooms = new ArrayList<>();
        this.random = new Random();
        this.objectFactories = new RoomObjectFactory[]{
                new EnemyFactory(),
                new PotionFactory(),
                new TrapFactory()
        };
        generateRooms(roomCount);
        iterator = new RoomIterator(rooms);
    }

    public void startExploration() {
        if (iterator.hasNext()) {
            exploreNextRoom();
        } else {
            System.out.println("The dungeon is empty!");
        }
    }

    public void exploreNextRoom() {
        if (iterator.hasNext()) {
            Room nextRoom = iterator.next();
            nextRoom.explore(Game.getInstance().getPlayer());
        } else {
            System.out.println("No more rooms left in the dungeon.");
        }
    }

    private void generateRooms(int roomCount) {
        for (int i = 0; i < roomCount; i++) {
            Room room = new Room("Room " + (i + 1));
            int objectType = random.nextInt(objectFactories.length);
            room.addObject(objectFactories[objectType].createObject());
            rooms.add(room);
        }

        rooms.getLast().setDescription("Room " + roomCount + ": you found the exit!");
    }

    public boolean isComplete() {
        return !iterator.hasNext();
    }

    public int getRoomCount() {
        return rooms.size();
    }
}
