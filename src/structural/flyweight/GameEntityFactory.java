package structural.flyweight;

import java.util.HashMap;

public class GameEntityFactory {
    private static final HashMap<String, GameEntity> entityCache = new HashMap<>();

    public static GameEntity getTree(String type) {
        // Get existing tree if available
        GameEntity tree = entityCache.get("Tree:" + type);

        if(tree == null) {
            // Create new tree if not in cache
            tree = new Tree(type);
            entityCache.put("Tree:" + type, tree);
        }

        return tree;
    }

    // Could be extended with other entity types (monsters, buildings, etc.)
}
