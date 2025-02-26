package dungeon;

import character.Enemy;
import iterator.RoomIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Dungeon {
    final private List<Room> rooms;
    final private Random random;

    public Dungeon(int roomCount) {
        this.rooms = new ArrayList<>();
        this.random = new Random();
        generateRooms(roomCount);
    }

    private void generateRooms(int roomCount) {
        for (int i = 0; i < roomCount; i++) {
            Room room = new Room("Ruangan " + (i + 1));
            int objectType = random.nextInt(3);
            switch (objectType) {
                case 0:
                    room.addObject(new Enemy("Goblin", 50, 10, 5));
                    break;
                case 1:
                    room.addObject(new Potion(20));
                    break;
                case 2:
                    room.addObject(new Trap(10));
                    break;
            }
            rooms.add(room);
        }

        // Ruangan terakhir adalah jalan keluar
        rooms.getLast().setDescription("Ruangan " + roomCount + ": Jalan keluar!");
    }

    public Iterator<Room> iterator() {
        return new RoomIterator(rooms);
    }
}
