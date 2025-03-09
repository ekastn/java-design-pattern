package structural.adapter;

import java.util.HashMap;
import java.util.Map;

public class ClassicControllerAdapter implements ModernController {
    private ClassicController classicController;
    private static final Map<String, Integer> BUTTON_MAPPING = new HashMap<>();

    static {
        BUTTON_MAPPING.put("A", 0);
        BUTTON_MAPPING.put("B", 1);
        BUTTON_MAPPING.put("SELECT", 2);
        BUTTON_MAPPING.put("START", 3);
    }

    public ClassicControllerAdapter(ClassicController classicController) {
        this.classicController = classicController;
    }

    @Override
    public void pressButton(String button) {
        if (BUTTON_MAPPING.containsKey(button.toUpperCase())) {
            classicController.buttonPress(BUTTON_MAPPING.get(button.toUpperCase()));
        } else {
            System.out.println("Button not supported on classic controller: " + button);
        }
    }

    @Override
    public void moveJoystick(int x, int y) {
        // Convert joystick movement to directional pad input
        if (Math.abs(x) > Math.abs(y)) {
            // Horizontal movement dominates
            if (x > 0) {
                classicController.directionPad("RIGHT");
            } else {
                classicController.directionPad("LEFT");
            }
        } else {
            // Vertical movement dominates
            if (y > 0) {
                classicController.directionPad("DOWN");
            } else {
                classicController.directionPad("UP");
            }
        }
    }

    @Override
    public void triggerAction(double pressure) {
        // Classic controller doesn't have triggers, so map to the A button if pressure exceeds threshold
        if (pressure > 0.5) {
            classicController.buttonPress(0); // A button
        }
    }
}
