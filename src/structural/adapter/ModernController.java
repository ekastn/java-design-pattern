package structural.adapter;

public interface ModernController {
    void pressButton(String button);
    void moveJoystick(int x, int y);
    void triggerAction(double pressure);
}
