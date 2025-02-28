package creational.factorymethod;

import character.Enemy;
import dungeon.RoomObject;

public class EnemyFactory extends RoomObjectFactory {
    @Override
    public RoomObject createObject() {
        return new Enemy("Goblin", 50, 10, 5);
    }
}
