package creational.factorymethod;

import dungeon.Potion;
import dungeon.RoomObject;

public class PotionFactory extends RoomObjectFactory {
    @Override
    public RoomObject createObject() {
        return new Potion(20);
    }
}
