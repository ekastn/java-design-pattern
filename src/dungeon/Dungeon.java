package dungeon;

import behavioral.iterator.RoomIterator;
import creational.factorymethod.*;
import structural.composite.Room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Dungeon {
    final private List<Room> rooms;
    final private Random random;
    final private RoomObjectFactory[] objectFactories;

    public Dungeon(int roomCount) {
        this.rooms = new ArrayList<>();
        this.random = new Random();
        this.objectFactories = new RoomObjectFactory[]{
                new EnemyFactory(),
                new PotionFactory(),
                new TrapFactory()
        };
        generateRooms(roomCount);
    }

    private void generateRooms(int roomCount) {
        for (int i = 0; i < roomCount; i++) {
            Room room = new Room("Ruangan " + (i + 1));
            int objectType = random.nextInt(objectFactories.length);
            room.addObject(objectFactories[objectType].createObject());
            rooms.add(room);
        }

        // Ruangan terakhir adalah jalan keluar
        rooms.getLast().setDescription("Ruangan " + roomCount + ": Jalan keluar!");
    }

    public Iterator<Room> iterator() {
        return new RoomIterator(rooms);
    }

    public int getRoomCount() {
        return rooms.size();
    }
}
