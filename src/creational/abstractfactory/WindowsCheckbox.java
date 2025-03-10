package creational.abstractfactory;

public class WindowsCheckbox implements Checkbox {
    private boolean checked = false;

    @Override
    public void render() {
        System.out.println("Rendering a Windows-style checkbox: [" + (checked ? "x" : " ") + "]");
    }

    @Override
    public void toggle() {
        checked = !checked;
        System.out.println("Windows checkbox toggled to: " + (checked ? "checked" : "unchecked"));
    }
}
