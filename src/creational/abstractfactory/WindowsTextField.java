package creational.abstractfactory;

public class WindowsTextField implements TextField {
    @Override
    public void render() {
        System.out.println("Rendering a Windows-style text field");
    }

    @Override
    public void getText() {
        System.out.println("Getting text from Windows text field");
    }
}
