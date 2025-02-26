package dungeon;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String description;
    private List<Object> objects;

    public Room(String description) {
        this.description = description;
        this.objects = new ArrayList<>();
    }

    public void addObject(Object object) {
        objects.add(object);
    }

    public List<Object> getObjects() {
        return objects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
