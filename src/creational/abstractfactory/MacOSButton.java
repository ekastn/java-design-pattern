package creational.abstractfactory;

public class MacOSButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering a MacOS-style button");
    }

    @Override
    public void onClick() {
        System.out.println("MacOS button clicked!");
    }
}
