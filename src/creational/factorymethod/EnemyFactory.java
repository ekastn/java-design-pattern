package creational.factorymethod;

import creational.prototype.EnemyPrototype;
import structural.composite.RoomObject;

import java.util.Random;

public class EnemyFactory extends RoomObjectFactory {
    private static final String[] ENEMY_TYPES = {"goblin", "orc", "skeleton", "troll"};
    private final Random random = new Random();

    @Override
    public RoomObject createObject() {
        String type = ENEMY_TYPES[random.nextInt(ENEMY_TYPES.length)];
        return EnemyPrototype.getPrototype(type);
    }
}
