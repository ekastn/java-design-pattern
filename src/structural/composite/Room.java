package structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String description;
    private List<RoomObject> objects;

    public Room(String description) {
        this.description = description;
        this.objects = new ArrayList<>();
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
