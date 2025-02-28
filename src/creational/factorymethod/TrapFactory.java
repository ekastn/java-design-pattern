package creational.factorymethod;

import structural.composite.RoomObject;
import structural.composite.Trap;

// src/dungeon/TrapFactory.java
public class TrapFactory extends RoomObjectFactory {
    @Override
    public RoomObject createObject() {
        return new Trap(10);
    }
}
