package creational.prototype;

import core.Enemy;
import java.util.HashMap;
import java.util.Map;

public class EnemyPrototype {
    private static final Map<String, Enemy> prototypes = new HashMap<>();

    static {
        prototypes.put("goblin", new Enemy("Goblin", 50, 10, 5));
        prototypes.put("orc", new Enemy("Orc", 80, 15, 8));
        prototypes.put("skeleton", new Enemy("Skeleton", 40, 12, 3));
        prototypes.put("troll", new Enemy("Troll", 100, 20, 10));
    }

    public static Enemy getPrototype(String type) {
        return (Enemy) prototypes.get(type).clone();
    }
}
