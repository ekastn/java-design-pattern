package structural.adapter;

public class Game {
    private final ModernController controller;

    public Game(ModernController controller) {
        this.controller = controller;
    }

    public void play() {
        System.out.println("Game started!");
        controller.pressButton("A");
        controller.moveJoystick(10, 0);
        controller.triggerAction(0.8);
        controller.pressButton("X");
        System.out.println("Game finished!");
    }
}
