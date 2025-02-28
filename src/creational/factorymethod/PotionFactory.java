package creational.factorymethod;

import structural.composite.Potion;
import structural.composite.RoomObject;

public class PotionFactory extends RoomObjectFactory {
    @Override
    public RoomObject createObject() {
        return new Potion(20);
    }
}
