package behavioral.iterator;

import dungeon.Room;

import java.util.Iterator;
import java.util.List;

public class RoomIterator implements Iterator<Room> {
    final private List<Room> rooms;
    private int position = 0;

    public RoomIterator(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean hasNext() {
        return position < rooms.size();
    }

    @Override
    public Room next() {
        return rooms.get(position++);
    }
}
