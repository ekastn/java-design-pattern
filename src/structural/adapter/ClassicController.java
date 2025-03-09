package structural.adapter;

public class ClassicController {
    public void buttonPress(int buttonId) {
        switch (buttonId) {
            case 0: System.out.println("Classic controller: A button pressed"); break;
            case 1: System.out.println("Classic controller: B button pressed"); break;
            case 2: System.out.println("Classic controller: Select pressed"); break;
            case 3: System.out.println("Classic controller: Start pressed"); break;
            default: System.out.println("Classic controller: Unknown button pressed"); break;
        }
    }

    public void directionPad(String direction) {
        System.out.println("Classic controller: D-pad moved " + direction);
    }
}
