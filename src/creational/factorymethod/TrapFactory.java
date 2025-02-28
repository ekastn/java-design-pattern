package creational.factorymethod;

import dungeon.RoomObject;
import dungeon.Trap;

// src/dungeon/TrapFactory.java
public class TrapFactory extends RoomObjectFactory {
    @Override
    public RoomObject createObject() {
        return new Trap(10);
    }
}
