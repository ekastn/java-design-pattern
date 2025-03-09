package structural.adapter;

public class PS5Controller implements ModernController {
    @Override
    public void pressButton(String button) {
        System.out.println("PS5 controller: " + button + " button pressed");
    }

    @Override
    public void moveJoystick(int x, int y) {
        System.out.println("PS5 controller: Joystick moved to (" + x + ", " + y + ")");
    }

    @Override
    public void triggerAction(double pressure) {
        System.out.println("PS5 controller: Trigger pressed with " + pressure + " pressure");
    }
}
