package creational.abstractfactory;

public class MacOSTextField implements TextField {
    @Override
    public void render() {
        System.out.println("Rendering a MacOS-style text field");
    }

    @Override
    public void getText() {
        System.out.println("Getting text from MacOS text field");
    }
}
