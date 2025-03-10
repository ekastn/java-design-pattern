package creational.abstractfactory;

public class MacOSCheckbox implements Checkbox {
    private boolean checked = false;

    @Override
    public void render() {
        System.out.println("Rendering a MacOS-style checkbox: " + (checked ? "✓" : "○"));
    }

    @Override
    public void toggle() {
        checked = !checked;
        System.out.println("MacOS checkbox toggled to: " + (checked ? "checked" : "unchecked"));
    }
}
